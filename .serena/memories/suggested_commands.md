# Suggested Commands (Windows / PowerShell)

## Maven (use `mvnw.cmd` or `mvn` if installed globally)
> CWD must be `E:\Robomain\Robomain` (set via `.vscode/settings.json` → `terminal.integrated.cwd`).
> If terminal CWD is wrong, use `-f "E:\Robomain\Robomain\pom.xml"` flag.

```powershell
# Compile only
mvn compile

# Run app (dev)
mvn spring-boot:run

# Run tests
mvn test

# Run specific test class
mvn test -Dtest=CleanArchitectureTest

# Package JAR (skip tests)
mvn package -DskipTests

# Clean build
mvn clean package -DskipTests
```

## PowerShell notes
- Chain commands with `;` not `&&`
- Pipe output: `mvn compile 2>&1 | Select-Object -Last 30`
- Run JAR directly: `java -jar target\Robomain-0.0.1-SNAPSHOT.jar`

## Environment Variables (REQUIRED — no defaults for secrets)
> Template for dev: `.env.example` → copy to `.env` (git-ignored), fill in values.

```powershell
# Minimum required to start app
$env:DB_PASSWORD = "..."       # PostgreSQL password
$env:JWT_SECRET = "..."        # base64, min 256-bit; generate: openssl rand -base64 64
```
All other vars have sensible localhost defaults (see `mem:tech_stack` for full table).

## Git Setup Notes
- `.gitignore` excludes: `target/`, `/META-INF/`, `/org/`, `.env*` (except `.env.example`), `*.log`, `*.iml`, `.idea/`, `.vscode/`, OS junk
- `.env.example` is tracked — update it when adding new env vars
- `.env` is NOT tracked

## External Services (must be running locally for dev)
- PostgreSQL on `localhost:5432`, DB `cmms`, user `cmms`, pass `robomain`
- Redis on `localhost:6379`

## API Access (after startup)
- Swagger UI: http://localhost:8080/swagger-ui.html
- API docs JSON: http://localhost:8080/api-docs
- Actuator health: http://localhost:8080/actuator/health
