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
```powershell
# Secrets — must set before running
$env:DB_PASSWORD = "robomain"
$env:JWT_SECRET = "dGhpcy1pcy1hLXZlcnktbG9uZy1zZWNyZXQta2V5LWZvci1yb2JvbWFpbi1jbW1zLWp3dC10b2tlbi0yMDI2"

# Optional (defaults exist)
$env:DB_URL = "jdbc:postgresql://localhost:5432/cmms"   # default already correct
$env:DB_USERNAME = "cmms"
$env:MAIL_USERNAME = "..."    # Gmail address
$env:MAIL_PASSWORD = "..."    # Gmail app password
```
> Template: copy `.env.example` → `.env` (ignored by git)
> Generate new JWT_SECRET: `openssl rand -base64 64`

## External Services (must be running locally for dev)
- PostgreSQL on `localhost:5432`, DB `cmms`, user `cmms`, pass `robomain`
- Redis on `localhost:6379`

## API Access (after startup)
- Swagger UI: http://localhost:8080/swagger-ui.html
- API docs JSON: http://localhost:8080/api-docs
- Actuator health: http://localhost:8080/actuator/health
