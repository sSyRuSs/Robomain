# Robomain — CMMS Backend: Project Overview

## Tech Stack
- **Java 21**, **Spring Boot 4.1.0**
- **Spring Data JPA** + Hibernate
- **Spring Security** + JWT (via UserTokenRepository)
- **Spring WebSocket** (real-time chat/notifications)
- **Spring Events** (domain events)
- **Flyway** (DB migrations, V1..V108)
- **SpringDoc OpenAPI 2.2.0** (Swagger UI)
- **Lombok** (boilerplate reduction)
- **Spring Actuator** (health/metrics)

## Package Root
`com.example.Robomain`

## Clean Architecture — 4 Layers
```
presentation/   → REST controllers, exception handlers, Swagger config
application/    → Commands, Queries, Handlers, DTOs, DtoMappers
domain/         → Entities, Repository Interfaces, Domain Events, Shared Exceptions/Enums
infrastructure/ → JPA Entities, JPA Repositories, Repository Impls, Infra Mappers, Security, Config
```

## Key Architectural Decisions (ADRs)
- **CQRS**: All command handlers return `UUID` (not DTO). Controllers chain getById query after create/update.
- **Command pattern**: `CreateXxxCommandHandler.handle(cmd) → UUID`, then controller calls `getByIdHandler.handle(new GetXxxByIdQuery(id))` to return full DTO.
- **Domain purity**: Domain entities have ZERO Spring/JPA imports — pure Java only.
- **Repository abstraction**: Interfaces in Domain (`IXxxRepository`), implementations in Infrastructure (`XxxRepositoryImpl`).
- **Dual mapper pattern**: Infrastructure mappers (`XxxMapper`) handle Domain↔JPA. Application mappers (`XxxDtoMapper`) handle Domain→DTO.
- **BaseJpaEntity**: All JPA entities extend it (contains `id`, `createdAt`, `updatedAt` with `@PrePersist`/`@PreUpdate`).
- **Exception hierarchy**: `DomainException` → `ConflictException`, `ResourceNotFoundException`, `ValidationException` (all in domain/shared).
- **Global exception handler**: `GlobalExceptionHandler` in presentation/api/exception.
