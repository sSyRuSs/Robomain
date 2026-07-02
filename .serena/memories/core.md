# Robomain — Core

CMMS (Computerized Maintenance Management System) backend. Spring Boot REST API.

## Source Root
`src/main/java/com/example/Robomain/`

## Top-Level Packages
| Package | Role |
|---|---|
| `domain/` | Pure Java entities, repository interfaces, domain events, enums, exceptions |
| `application/` | CQRS commands, queries, handlers, DTOs, DtoMappers |
| `infrastructure/` | JPA entities, Spring Data repos, repo impls, infra mappers, security |
| `presentation/` | REST controllers, GlobalExceptionHandler, SwaggerConfig |

## Entry Point
`RobomainApplication.java` — `@SpringBootApplication`

## Key Cross-Cutting Files
- `application/shared/Constants.java` — all API path constants (`/api/v1/...`)
- `application/shared/response/ApiResponse<T>` — universal response wrapper
- `application/shared/response/PaginationResponse<T>` — paginated list wrapper
- `infrastructure/persistence/base/BaseJpaEntity` — base for all JPA entities (`UUID id`, `createdAt`, `updatedAt`)
- `domain/shared/exception/DomainException` — base exception hierarchy

## Module Count
27 domain modules. For full module→symbol mapping see `mem:architecture/module-symbol-map`.

## References
- Tech stack details: `mem:tech_stack`
- Layer-by-layer symbols: `mem:architecture/domain-layer`, `mem:architecture/application-layer`, `mem:architecture/infrastructure-layer`, `mem:architecture/presentation-layer`
- CQRS + Clean Architecture conventions: `mem:conventions`
- Build/run commands: `mem:suggested_commands`
- Task completion steps: `mem:task_completion`
