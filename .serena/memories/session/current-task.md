# Current Task

Task: Tăng tốc performance cho module Task và WorkOrder
Status: completed
Scope: infrastructure/persistence, application/task, application/workorder, infrastructure/config
Changes:
- @EnableCaching thêm vào RobomainApplication (cache trước đây hoàn toàn vô hiệu)
- RedisConfig: RedisCacheManager bean cho cache `task` và `work_order` TTL 300s
- GetTaskByIdQueryHandler: @Cacheable
- UpdateTaskCommandHandler: @CacheEvict
- GetWorkOrderByIdQueryHandler: @Cacheable
- UpdateWorkOrderCommandHandler: @CacheEvict
- CreateTaskCommandHandler: bỏ fetch+save pattern → incrementTaskTotal 1 UPDATE
- IWorkOrderRepository: thêm incrementTaskTotal(UUID)
- WorkOrderJpaRepository: @Modifying @Query UPDATE trực tiếp
- V109__task_workorder_perf_indexes.sql: GIN trigram indexes + composite index task_detail
Next: chạy mvn compile và CleanArchitectureTest để xác nhận
