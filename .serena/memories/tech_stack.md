# Tech Stack

## Runtime
- **Java 21**
- **Spring Boot 4.1.0** (spring-boot-starter-parent)
- **PostgreSQL** — primary DB (`jdbc:postgresql://localhost:5432/cmms`, user: `cmms`)
- **Redis** — cache + session (`localhost:6379`, TTL 600s)
- Server port: **8080**

## Spring Starters
- `spring-boot-starter-web`, `spring-boot-starter-data-jpa`
- `spring-boot-starter-security`, `spring-boot-starter-websocket`
- `spring-boot-starter-data-redis`, `spring-boot-starter-cache`
- `spring-boot-starter-actuator`, `spring-boot-starter-thymeleaf`

## Key Libraries (pinned versions)
| Library | Version |
|---|---|
| springdoc-openapi-starter-webmvc-ui | **3.0.0** (Spring Boot 4.x compatible) → Swagger at `/swagger-ui.html`, docs at `/api-docs` |
| spring-boot-starter-flyway | managed by Boot 4.1.0 → pulls flyway-core **12.4.0** (autoconfiguration module required in SB4) |
| flyway-database-postgresql | **12.4.0** (managed by Boot, no explicit version) |
| spring-boot-starter-validation | managed by Boot (Hibernate Validator) |
| jjwt-api/impl/jackson | 0.11.5 |
| archunit-junit5 | 1.3.0 (test scope) |
| modelmapper | 3.2.0 |
| lombok | managed by Boot |
| mockito-core + mockito-junit-jupiter | managed by Boot (test) |
| google-api-services-drive | v3-rev20240521-2.0.0 |
| google-maps-services | 0.2.9 |
| zxing (QR) | 3.5.2 |
| openpdf | 2.0.3 |
| apache-poi + poi-ooxml | 5.2.3 |

## Build Tool
**Maven** — wrapper scripts: `mvnw` (Unix), `mvnw.cmd` (Windows)

## JPA Strategy
- `ddl-auto: validate` — schema managed exclusively by Flyway (never auto-create)
- `open-in-view: false`
- `show-sql: false`

## Security
- JWT: secret from env `JWT_SECRET` (base64 default present for dev), access 24h, refresh 7d
- Rate limiting: `RateLimitService` in `infrastructure/security/`

## Config
`src/main/resources/application.yaml` — sensitive values via env vars: `JWT_SECRET`, `MAIL_USERNAME`, `MAIL_PASSWORD`
