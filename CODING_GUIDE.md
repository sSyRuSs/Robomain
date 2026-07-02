# Robomain CMMS — Coding Guide

> **Stack**: Java 21 · Spring Boot 4.1.0 · PostgreSQL · Redis  
> **Architecture**: Clean Architecture + CQRS (enforced by ArchUnit)  
> **Package root**: `com.example.Robomain`

---

## Table of Contents

1. [Architecture Overview](#1-architecture-overview)
2. [Package Structure](#2-package-structure)
3. [CQRS Pattern](#3-cqrs-pattern)
4. [Domain Layer Rules](#4-domain-layer-rules)
5. [Application Layer Rules](#5-application-layer-rules)
6. [Infrastructure Layer Rules](#6-infrastructure-layer-rules)
7. [Presentation Layer Rules](#7-presentation-layer-rules)
8. [Naming Conventions](#8-naming-conventions)
9. [Exception Handling](#9-exception-handling)
10. [Adding a New Feature](#10-adding-a-new-feature)
11. [Database Migrations](#11-database-migrations)
12. [Testing](#12-testing)
13. [Running the App](#13-running-the-app)

---

## 1. Architecture Overview

The project follows **Clean Architecture** with a strict one-way dependency rule:

```
Presentation  →  Application  →  Domain
                                    ↑
                          Infrastructure
```

| Layer | Purpose | May depend on |
|---|---|---|
| **Domain** | Business entities, rules, interfaces | Nothing (pure Java) |
| **Application** | Use cases: commands, queries, handlers | Domain only |
| **Infrastructure** | DB, JPA, Redis, external services | Domain (implements interfaces) |
| **Presentation** | REST controllers, security, Swagger | Application |

**The Golden Rule**: Inner layers never know about outer layers. Domain imports nothing from Spring, JPA, or any framework.

These rules are **automatically enforced** — `CleanArchitectureTest` runs 14 ArchUnit checks and will fail the build on any violation.

---

## 2. Package Structure

```
src/main/java/com/example/Robomain/
├── domain/
│   ├── {module}/
│   │   ├── model/          ← Domain entity (pure Java)
│   │   ├── repository/     ← IXxxRepository interface
│   │   └── event/          ← XxxCreatedEvent (optional)
│   └── shared/
│       └── exception/      ← Domain exceptions
├── application/
│   ├── {module}/
│   │   ├── command/        ← CreateXxxCommand, UpdateXxxCommand
│   │   ├── query/          ← GetXxxByIdQuery, ListXxxQuery
│   │   ├── handler/        ← Command and query handlers
│   │   ├── dto/            ← XxxDto (read model)
│   │   └── mapper/         ← XxxDtoMapper (@Component)
│   └── shared/
│       ├── Constants.java  ← All API path constants
│       └── response/       ← ApiResponse, PaginationResponse
├── infrastructure/
│   ├── persistence/
│   │   ├── entity/         ← XxxJpaEntity (extends BaseJpaEntity)
│   │   ├── jpa/            ← XxxJpaRepository (Spring Data)
│   │   ├── mapper/         ← XxxMapper (domain ↔ JPA)
│   │   └── repository/     ← XxxRepositoryImpl (implements IXxxRepository)
│   ├── security/           ← JWT, filters, rate limiting
│   └── config/             ← Redis, ModelMapper beans
└── presentation/
    ├── api/
    │   ├── controller/     ← XxxController
    │   └── exception/      ← GlobalExceptionHandler
    └── config/             ← SwaggerConfig, WebSecurityConfig
```

---

## 3. CQRS Pattern

### Commands (write operations)

Commands mutate state. **All command handlers must return `UUID`** — the ID of the created/updated entity. Never return a DTO from a command handler.

```java
// ✅ Correct
@Service
@RequiredArgsConstructor
public class CreateAssetCommandHandler {

    private final IAssetRepository assetRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public UUID handle(CreateAssetCommand command) {
        Asset asset = Asset.create(command.getName(), command.getCode(), ...);
        Asset saved = assetRepository.save(asset);
        eventPublisher.publishEvent(new AssetCreatedEvent(saved.getId()));
        return saved.getId();   // ← always UUID, never DTO
    }
}

// ❌ Wrong — never return DTO from a command handler
public AssetDto handle(CreateAssetCommand command) { ... }
```

### Queries (read operations)

Queries never change state. Query handlers are `@Transactional(readOnly = true)` and return a DTO.

```java
@Service
@RequiredArgsConstructor
public class GetAssetByIdQueryHandler {

    private final IAssetRepository repository;
    private final AssetDtoMapper dtoMapper;

    @Transactional(readOnly = true)
    public AssetDto handle(GetAssetByIdQuery query) {
        Asset asset = repository.findById(query.getAssetId())
                .orElseThrow(() -> new ResourceNotFoundException("Asset", query.getAssetId()));
        return dtoMapper.toDto(asset);
    }
}
```

### Controller pattern (mandatory)

After a create/update command, the controller **must** call the `getById` query to return the full DTO. This keeps commands pure (return UUID only) while still giving the caller the created resource.

```java
// POST — create
@PostMapping
public ResponseEntity<ApiResponse<AssetDto>> create(@Valid @RequestBody CreateAssetCommand cmd) {
    UUID id = createHandler.handle(cmd);
    return ResponseEntity.status(HttpStatus.CREATED)
            .body(ApiResponse.success(getByIdHandler.handle(new GetAssetByIdQuery(id))));
}

// PUT — update
@PutMapping("/{id}")
public ResponseEntity<ApiResponse<AssetDto>> update(@PathVariable UUID id,
                                                     @RequestBody UpdateAssetCommand cmd) {
    cmd.setAssetId(id);
    UUID updatedId = updateHandler.handle(cmd);
    return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetAssetByIdQuery(updatedId))));
}

// GET — read (query only, no command involved)
@GetMapping("/{id}")
public ResponseEntity<ApiResponse<AssetDto>> getById(@PathVariable UUID id) {
    return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetAssetByIdQuery(id))));
}
```

---

## 4. Domain Layer Rules

> **Domain = pure Java. Zero framework imports.**

### Entity design

```java
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Asset {

    private UUID id;
    private String assetName;
    private UUID facilityId;
    // ...

    // Factory method — the only way to create a new entity
    public static Asset create(String name, String code, UUID facilityId, UUID categoryId) {
        if (name == null || name.isBlank())
            throw new ValidationException("Asset name cannot be blank");
        return Asset.builder()
                .id(UUID.randomUUID())
                .assetName(name)
                .assetCode(code)
                .facilityId(facilityId)
                .assetCategoryId(categoryId)
                .build();
    }

    // Mutation method — centralises all field updates
    public void update(String name, String description) {
        if (name != null && !name.isBlank()) this.assetName = name;
        if (description != null)             this.assetDescription = description;
    }
}
```

Rules:
- Use `static create(...)` for construction — validates invariants before the object exists.
- Use `void update(...)` instance methods for mutations.
- Throw `ValidationException` / `ConflictException` / `ResourceNotFoundException` inline (from `domain.shared.exception`).
- `Optional<UUID>` return type for nullable foreign key fields.
- **No `@Entity`, `@Column`, `@Service`, `@Component`** — none of these belong in domain.

### Repository interfaces

```java
// domain/asset/repository/IAssetRepository.java
public interface IAssetRepository {
    Asset save(Asset asset);
    Optional<Asset> findById(UUID id);
    List<Asset> findAll(int page, int size, String search, UUID facilityId);
    boolean existsByAssetCode(String code);
    void deleteById(UUID id);
}
```

Only domain types in signatures — no JPA `Page`, `Pageable`, or Spring types.

### Domain events (optional)

```java
// domain/asset/event/AssetCreatedEvent.java
public record AssetCreatedEvent(UUID assetId, String name, UUID facilityId) {}
```

Publish via `ApplicationEventPublisher` from the **command handler** (not the domain entity).

---

## 5. Application Layer Rules

> Application orchestrates domain. No Spring Data, no JPA, no DB access directly.

### Dual mapper pattern

Two distinct mapper types exist. **Never mix them.**

| Type | Class name | Package | Method | Dependency |
|---|---|---|---|---|
| **App mapper** | `XxxDtoMapper` | `application/{module}/mapper/` | `toDto(Domain) → XxxDto` | `@Component`, injected into query handlers |
| **Infra mapper** | `XxxMapper` | `infrastructure/persistence/mapper/` | `toDomain(JpaEntity)` / `toJpa(Domain)` | Used only inside `XxxRepositoryImpl` |

```java
// application/asset/mapper/AssetDtoMapper.java
@Component
public class AssetDtoMapper {
    public AssetDto toDto(Asset asset) {
        return AssetDto.builder()
                .id(asset.getId())
                .assetName(asset.getAssetName())
                // ...
                .build();
    }
}
```

### Command class

```java
// application/asset/command/CreateAssetCommand.java
@Getter @Setter
public class CreateAssetCommand {
    @NotBlank private String assetName;
    @NotBlank private String assetCode;
    @NotNull  private UUID facilityId;
    private   String assetDescription;
}
```

### DTO class

```java
// application/asset/dto/AssetDto.java
@Getter @Builder
public class AssetDto {
    private UUID   id;
    private String assetName;
    private String assetCode;
    // ...
}
```

---

## 6. Infrastructure Layer Rules

### JPA entity

```java
// infrastructure/persistence/entity/AssetJpaEntity.java
@Entity
@Table(name = "asset")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class AssetJpaEntity extends BaseJpaEntity {   // BaseJpaEntity provides id, createdAt, updatedAt

    @Column(name = "asset_name", nullable = false)
    private String assetName;

    @Column(name = "asset_code", unique = true)
    private String assetCode;

    @Column(name = "facility_id")
    private UUID facilityId;
}
```

- Table names match Flyway migration scripts (source of truth).
- `@Entity` and `@Table` stay in Infrastructure, never in Domain.

### Infra mapper

```java
// infrastructure/persistence/mapper/AssetMapper.java
@Component
public class AssetMapper {

    public Asset toDomain(AssetJpaEntity e) {
        return Asset.builder()
                .id(e.getId())
                .assetName(e.getAssetName())
                .build();
    }

    public AssetJpaEntity toJpa(Asset asset) {
        AssetJpaEntity e = new AssetJpaEntity();
        e.setId(asset.getId());
        e.setAssetName(asset.getAssetName());
        return e;
    }
}
```

### Repository implementation

```java
// infrastructure/persistence/repository/AssetRepositoryImpl.java
@Repository
@RequiredArgsConstructor
public class AssetRepositoryImpl implements IAssetRepository {

    private final AssetJpaRepository jpaRepository;
    private final AssetMapper mapper;

    @Override
    public Asset save(Asset asset) {
        return mapper.toDomain(jpaRepository.save(mapper.toJpa(asset)));
    }

    @Override
    public Optional<Asset> findById(UUID id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
}
```

---

## 7. Presentation Layer Rules

### API paths

All paths are constants in `application/shared/Constants.java`:

```java
public class Constants {
    public static final String ASSET_PATH    = "/api/v1/assets";
    public static final String FACILITY_PATH = "/api/v1/facilities";
    // ...
}
```

Never hardcode path strings in a controller.

### Response shapes

```java
// Single resource
ApiResponse.success(dto)
// → { "data": { ...dto fields... } }

// Paginated list
ApiResponse.success(new PaginationResponse<>(content, total))
// → { "data": { "content": [...], "total": 42 } }
```

### Security — public endpoints

Add new public paths to **both** locations:

1. `WebSecurityConfig` — `requestMatchers(...)`.`permitAll()`
2. `AuthTokenFilter` — `isPublicPath()` method

---

## 8. Naming Conventions

| Type | Pattern | Example |
|---|---|---|
| Domain entity | `PascalCase` | `Asset`, `WorkOrder` |
| Domain repo interface | `IXxxRepository` | `IAssetRepository` |
| Command | `VerbNounCommand` | `CreateAssetCommand` |
| Query | `VerbNounQuery` | `GetAssetByIdQuery`, `ListAssetsQuery` |
| Command handler | `VerbNounCommandHandler` | `CreateAssetCommandHandler` |
| Query handler | `VerbNounQueryHandler` | `GetAssetByIdQueryHandler` |
| Application DTO mapper | `XxxDtoMapper` | `AssetDtoMapper` |
| Application DTO | `XxxDto` | `AssetDto` |
| JPA entity | `XxxJpaEntity` | `AssetJpaEntity` |
| JPA repository | `XxxJpaRepository` | `AssetJpaRepository` |
| Infrastructure mapper | `XxxMapper` | `AssetMapper` |
| Repository impl | `XxxRepositoryImpl` | `AssetRepositoryImpl` |
| Controller | `XxxController` | `AssetController` |
| Domain event | `XxxCreatedEvent` | `AssetCreatedEvent` |

---

## 9. Exception Handling

### Domain exceptions (throw from anywhere in domain/application)

```java
// 404 — resource not found
throw new ResourceNotFoundException("Asset", assetId);
throw new ResourceNotFoundException("Asset", "code: " + code);

// 409 — conflict (duplicate)
throw new ConflictException("Asset", "assetCode", command.getAssetCode());

// 400 — invalid input
throw new ValidationException("Asset name cannot be blank");
```

`GlobalExceptionHandler` in the presentation layer maps these automatically:

| Exception | HTTP status |
|---|---|
| `ResourceNotFoundException` | 404 |
| `ConflictException` | 409 |
| `ValidationException` | 400 |
| Any other `Exception` | 500 |

### Never catch silently

```java
// ❌ Never do this
try {
    repository.save(entity);
} catch (Exception e) {
    // swallowed
}

// ✅ Let domain exceptions propagate; catch only to translate
```

---

## 10. Adding a New Feature

Follow this order — **always domain-first**.

### Step 1 — Domain

```
domain/{module}/model/XxxEntity.java
domain/{module}/repository/IXxxRepository.java
domain/{module}/event/XxxCreatedEvent.java   (optional)
```

### Step 2 — Application

```
application/{module}/command/CreateXxxCommand.java
application/{module}/command/UpdateXxxCommand.java
application/{module}/query/GetXxxByIdQuery.java
application/{module}/query/ListXxxQuery.java
application/{module}/handler/CreateXxxCommandHandler.java
application/{module}/handler/UpdateXxxCommandHandler.java
application/{module}/handler/GetXxxByIdQueryHandler.java
application/{module}/handler/ListXxxQueryHandler.java
application/{module}/dto/XxxDto.java
application/{module}/mapper/XxxDtoMapper.java
```

### Step 3 — Infrastructure

```
infrastructure/persistence/entity/XxxJpaEntity.java
infrastructure/persistence/jpa/XxxJpaRepository.java
infrastructure/persistence/mapper/XxxMapper.java
infrastructure/persistence/repository/XxxRepositoryImpl.java
```

### Step 4 — Presentation

```
presentation/api/controller/XxxController.java
application/shared/Constants.java  ← add XXX_PATH constant
```

### Step 5 — Migration

```
src/main/resources/db/migration/V{n}__create_table_xxx.sql
```

### Step 6 — Verify

```powershell
mvn compile -q
mvn test -Dtest=CleanArchitectureTest -q
```

---

## 11. Database Migrations

- **Flyway** manages all schema changes. `ddl-auto: validate` means Hibernate will fail at startup if the schema doesn't match — this catches missing migrations early.
- Migration files: `src/main/resources/db/migration/V{n}__{description}.sql`
- **Never modify** an existing migration that has been applied (`flyway_schema_history` tracks checksums).
- Always add a new `V{n+1}__...sql` for any schema change.
- Current latest: **V108**

```sql
-- Example: V109__add_priority_to_work_order.sql
ALTER TABLE work_order ADD COLUMN priority VARCHAR(20) DEFAULT 'MEDIUM';
```

---

## 12. Testing

### Run all tests

```powershell
mvn test
```

### Run architecture guard only

```powershell
mvn test -Dtest=CleanArchitectureTest
```

This checks 14 rules including:
- Layer dependency direction
- Domain has no Spring/JPA imports
- Application has no infrastructure imports
- Controllers are in the presentation package
- Handlers are named correctly

### After any refactor, always run the architecture test first

A passing compile does not mean Clean Architecture rules are satisfied.

---

## 13. Running the App

### Prerequisites

- PostgreSQL running on `localhost:5432`, database `cmms`, user `cmms`, password `robomain`
- Redis running on `localhost:6379`

### Start

```powershell
mvn spring-boot:run -f "E:\Robomain\Robomain\pom.xml"
```

Success indicator: `Started RobomainApplication in X seconds`

### Key URLs

| URL | Description |
|---|---|
| `http://localhost:8080/swagger-ui.html` | Swagger UI |
| `http://localhost:8080/api-docs` | OpenAPI JSON |
| `http://localhost:8080/actuator/health` | Health check |

### Environment variables (optional for dev, required for production)

```powershell
$env:JWT_SECRET      = "<base64 encoded secret, min 256-bit>"
$env:MAIL_USERNAME   = "<gmail address>"
$env:MAIL_PASSWORD   = "<app password>"
```

### Package for deployment

```powershell
mvn package -DskipTests
java -jar target\Robomain-0.0.1-SNAPSHOT.jar
```

---

## Quick Reference — Common Mistakes

| Mistake | Why it's wrong | Fix |
|---|---|---|
| Command handler returns `XxxDto` | Breaks CQRS — commands must not read | Return `UUID`; controller calls `getByIdHandler` |
| Domain entity imports `@Entity` | Violates domain purity — ArchUnit will fail | Move JPA annotation to `XxxJpaEntity` in Infrastructure |
| Handler injects `XxxJpaRepository` directly | Application bypasses domain interface | Inject `IXxxRepository` instead |
| Hardcoded `/api/v1/xxx` in controller | Duplicates path definition | Use `Constants.XXX_PATH` |
| Schema change without Flyway migration | Hibernate validation fails on startup | Add `V{n}__...sql` migration |
| New `XxxDtoMapper` without `@Component` | Spring can't inject it → `BeanCreationException` | Add `@Component` |
| `static toDto()` inside a command handler | Handler reads — violates CQRS SRP | Create a dedicated `XxxDtoMapper` |
