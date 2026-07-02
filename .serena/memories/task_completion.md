# Task Completion

## After every code change, run in order:

### 1. Compile (catch syntax/type errors)
```powershell
mvn compile -q
```
Expected: no output (silent = success). Any `[ERROR]` = fix before continuing.

### 2. Run Architecture Tests (enforce Clean Architecture rules)
```powershell
mvn test -Dtest=CleanArchitectureTest -q
```
14 ArchUnit rules enforced. Catches layer violations, JPA in domain, etc.

### 3. Run Full Test Suite
```powershell
mvn test -q
```

### 4. Verify app starts (if config/wiring changed)
```powershell
mvn spring-boot:run
```
Check: `Started RobomainApplication` in output, no `BeanCreationException`.

## Common Failure Causes
- **Compile error** `incompatible types: UUID vs XxxDto` → command handler still returns DTO, not UUID
- **ArchUnit failure** `domain should not depend on Spring/JPA` → domain entity imported framework class
- **ArchUnit failure** `application should not depend on infrastructure` → handler injected JPA repo directly
- **BeanCreationException at startup** → missing `@Component` on a DtoMapper or mapper not injected into handler constructor
- **Flyway migration error** → new DB column without corresponding migration script

## No linter/formatter configured
No Checkstyle, Spotless, or similar. Compilation + ArchUnit tests are the gatekeepers.
