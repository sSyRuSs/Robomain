# Conventions

## Architecture: Clean Architecture + CQRS (strictly enforced by ArchUnit)
Enforced via `CleanArchitectureTest` (14 rules). Violations = test failure.

### Layer Dependency Rule
```
presentation → application → domain ← infrastructure
```
- Domain: NO Spring, NO JPA imports — pure Java only
- Application: NO Infrastructure or Presentation imports
- Infrastructure: implements domain interfaces

### CQRS Handler Contract
- **All command handlers** return `UUID` (the ID of the created/mutated entity)
  - Never return a full DTO from a command handler
- **All query handlers**: `@Transactional(readOnly = true)`, return `XxxDto`
- **Controller pattern** (mandatory):
  ```java
  UUID id = createHandler.handle(cmd);
  return ResponseEntity.status(201).body(ApiResponse.success(getByIdHandler.handle(new GetXxxByIdQuery(id))));
  ```
- Exception: `ChatController.sendMessage` returns `ApiResponse<UUID>` (no GetMessageById handler)

### Naming Conventions
| Type | Pattern | Example |
|---|---|---|
| Domain entity | `PascalCase` | `Asset`, `WorkOrder` |
| Domain repo interface | `IXxxRepository` | `IAssetRepository` |
| Command | `VerbNounCommand` | `CreateAssetCommand` |
| Query | `VerbNounQuery` | `GetAssetByIdQuery`, `ListAssetsQuery` |
| Command handler | `VerbNounCommandHandler` | `CreateAssetCommandHandler` |
| Query handler | `VerbNounQueryHandler` | `GetAssetByIdQueryHandler` |
| App DTO mapper | `XxxDtoMapper` | `AssetDtoMapper` (in `application/{module}/mapper/`) |
| JPA entity | `XxxJpaEntity` | `AssetJpaEntity` (extends `BaseJpaEntity`) |
| Infra mapper | `XxxMapper` | `AssetMapper` (in `infrastructure/persistence/mapper/`) |
| Infra repo impl | `XxxRepositoryImpl` | `AssetRepositoryImpl` |
| Controller | `XxxController` | `AssetController` |

### Dual Mapper Pattern
- `XxxMapper` (infra): `toDomain(JpaEntity)` / `toJpa(Domain)` — lives in Infrastructure
- `XxxDtoMapper` (app): `toDto(Domain)` — `@Component`, lives in Application
- These must NOT be mixed or cross-referenced between layers

### Domain Entity Design
- Lombok `@Getter @Builder @AllArgsConstructor @NoArgsConstructor`
- Factory method `static Entity create(...)` with guard clauses
- Instance method `void update(...)` for mutations
- Business rules (guard clauses) throw domain exceptions inline
- `Optional<UUID>` return type for nullable foreign key fields (e.g. `getParentAssetId()`)

### Exception Hierarchy (domain/shared/exception)
- `DomainException` (base RuntimeException)
  - `ConflictException(resourceType, field, value)` → 409
  - `ResourceNotFoundException(resourceType, id)` → 404
  - `ValidationException(message)` → 400
- `GlobalExceptionHandler` in presentation maps these to HTTP codes

### API Response Shape
```json
{ "data": {...} }                            // ApiResponse<T>
{ "data": { "content": [...], "total": n }}  // ApiResponse<PaginationResponse<T>>
```

### Package Structure per Module
```
application/{module}/
  command/   → XxxCommand.java
  query/     → GetXxxByIdQuery.java, ListXxxQuery.java
  handler/   → XxxCommandHandler.java, XxxQueryHandler.java
  dto/       → XxxDto.java
  mapper/    → XxxDtoMapper.java
domain/{module}/
  model/     → XxxEntity.java
  repository/→ IXxxRepository.java
  event/     → XxxCreatedEvent.java (optional)
infrastructure/persistence/
  entity/    → XxxJpaEntity.java
  jpa/       → XxxJpaRepository.java (Spring Data)
  mapper/    → XxxMapper.java
  repository/→ XxxRepositoryImpl.java
```
