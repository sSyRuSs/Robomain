# Current Task

Task: Bug fixes — JWT startup fail, ObjectOptimisticLockingFailureException on CreateUser, env var config
Status: completed
Scope: infrastructure/persistence/base, infrastructure/persistence/repository/UserRepositoryImpl, application.yaml, pom.xml, .gitignore, .env

## Changes Made This Session

### .gitignore (Robomain/.gitignore)
- Restructured: added /META-INF/, /org/, .env/env.*, cred.json, *.log, OS junk

### application.yaml
- All hardcoded values → env vars with sensible defaults
- DB: ${DB_URL:...}, ${DB_USERNAME:cmms}, ${DB_PASSWORD:robomain}
- Redis: ${REDIS_HOST:localhost}, ${REDIS_PORT:6379}
- JWT: ${JWT_SECRET:base64fallback}, ${JWT_EXPIRATION_MS:86400000}, ${JWT_REFRESH_EXPIRATION_MS:604800000}
- Server: ${SERVER_PORT:8080}

### dotenv integration (pom.xml + new files)
- Added dotenv-java 3.1.0 dependency
- DotEnvEnvironmentPostProcessor.java (infrastructure/config)
- src/main/resources/META-INF/spring/org.springframework.boot.env.EnvironmentPostProcessor.imports
- .env file (git-ignored), .env.example (tracked)

### Performance: Task + WorkOrder (from previous task)
- @EnableCaching on RobomainApplication
- RedisConfig: RedisCacheManager bean, task/work_order caches TTL 300s
- @Cacheable on GetTaskByIdQueryHandler, GetWorkOrderByIdQueryHandler
- @CacheEvict on UpdateTaskCommandHandler, UpdateWorkOrderCommandHandler
- CreateTaskCommandHandler: incrementTaskTotal() direct UPDATE (was fetch+save)
- IWorkOrderRepository: incrementTaskTotal(UUID) interface
- WorkOrderJpaRepository: @Modifying @Query UPDATE
- V109__task_workorder_perf_indexes.sql: GIN trigram + composite indexes

### Bug Fix: ObjectOptimisticLockingFailureException on POST /api/v1/user
Root cause: User.create() pre-assigns UUID.randomUUID() → copied to JPA entity
→ Spring Data JPA sees non-null id → calls merge() instead of persist()
→ row doesn't exist → StaleObjectStateException (Hibernate 7.4.1)

Fix: UserRepositoryImpl.save() — only copy domain UUID to JPA entity when
     user.getCreatedAt() != null (= existing entity / update operation).
     New entities: id=null on JPA entity → @GeneratedValue assigns UUID → persist().

### Agent system
- .github/copilot-instructions.md: task routing workflow (small→SBA direct, large→Plan Agent)
- .github/agents/plan-agent.agent.md: Plan Agent (read/search/agent/serena only)
- Senior Backend Architect agent already existed

## Known State
- App compiles clean (mvn compile -q exit 0)
- JWT startup error fixed (fallback default restored)
- CreateUser fix compiled, NOT yet runtime-tested (app not started after fix)
- POST /api/v1/user still permitted without auth (should revert after first user created)

## Next
- Start app and test POST /api/v1/user to confirm fix works
- Create first admin user
- Revert POST /api/v1/user public access (WebSecurityConfig + AuthTokenFilter)
