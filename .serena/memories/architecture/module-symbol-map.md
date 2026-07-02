# Robomain — File Naming Conventions & Quick Reference

## Domain Entity → Full Symbol Map (for all 24 modules)
| Module folder | Domain Entity | Domain Repo Interface | Application DtoMapper | JPA Entity | Infra Mapper |
|---|---|---|---|---|---|
| asset | Asset | IAssetRepository | AssetDtoMapper | AssetJpaEntity | AssetMapper |
| asset_category | AssetCategory | IAssetCategoryRepository | AssetCategoryDtoMapper | AssetCategoryJpaEntity | AssetCategoryMapper |
| asset_status | AssetStatus | IAssetStatusRepository | AssetStatusDtoMapper | AssetStatusJpaEntity | AssetStatusMapper |
| asset_status_history | AssetStatusHistory | IAssetStatusHistoryRepository | — | AssetStatusHistoryJpaEntity | AssetStatusHistoryMapper |
| auth | UserToken | IUserTokenRepository | — | UserTokenJpaEntity | UserTokenMapper |
| bill | Bill | IBillRepository | BillDtoMapper | BillJpaEntity | BillMapper |
| bill_category | BillCategory | IBillCategoryRepository | BillCategoryDtoMapper | BillCategoryJpaEntity | BillCategoryMapper |
| chat | ChatConversation, ChatMessage | IConversationRepository, IChatMessageRepository | ChatConversationDtoMapper, ChatMessageDtoMapper | ChatConversationJpaEntity, ChatMessageJpaEntity | ChatConversationMapper, ChatMessageMapper |
| department | Department | IDepartmentRepository | DepartmentDtoMapper | DepartmentJpaEntity | DepartmentMapper |
| enterprise | Enterprise | IEnterpriseRepository | EnterpriseDtoMapper | EnterpriseJpaEntity | EnterpriseMapper |
| facility | Facility | IFacilityRepository | FacilityDtoMapper | FacilityJpaEntity | FacilityMapper |
| inventory | Inventory | IInventoryRepository | InventoryDtoMapper | InventoryJpaEntity | InventoryMapper |
| inventory_category | InventoryCategory | IInventoryCategoryRepository | InventoryCategoryDtoMapper | InventoryCategoryJpaEntity | InventoryCategoryMapper |
| maintenance | Maintenance | IMaintenanceRepository | MaintenanceDtoMapper | MaintenanceJpaEntity | MaintenanceMapper |
| notification | Notification | INotificationRepository | NotificationDtoMapper | NotificationJpaEntity | NotificationMapper |
| preventive_maintenance | PreventiveMaintenanceSchedule | IPreventiveMaintenanceScheduleRepository | PreventiveMaintenanceScheduleDtoMapper | PreventiveMaintenanceScheduleJpaEntity | PreventiveMaintenanceScheduleMapper |
| purchase_request | PurchaseRequest | IPurchaseRequestRepository | PurchaseRequestDtoMapper | PurchaseRequestJpaEntity | PurchaseRequestMapper |
| response_issue | ResponseIssue | IResponseIssueRepository | ResponseIssueDtoMapper | ResponseIssueJpaEntity | ResponseIssueMapper |
| role | Role | IRoleRepository | — | RoleJpaEntity | — |
| spare_part_reservation | SparePartReservation | ISparePartReservationRepository | SparePartReservationDtoMapper | SparePartReservationJpaEntity | SparePartReservationMapper |
| stock_movement | StockMovement | IStockMovementRepository | StockMovementDtoMapper | StockMovementJpaEntity | StockMovementMapper |
| task | Task | ITaskRepository | TaskDtoMapper | TaskJpaEntity | TaskMapper |
| task_detail | TaskDetail | ITaskDetailRepository | TaskDetailDtoMapper | TaskDetailJpaEntity | TaskDetailMapper |
| user | User | IUserRepository | — | UserJpaEntity, UserRoleJpaEntity | UserMapper |
| vendor | Vendor | IVendorRepository | VendorDtoMapper | VendorJpaEntity | VendorMapper |
| warehouse | Warehouse | IWarehouseRepository | WarehouseDtoMapper | WarehouseJpaEntity | WarehouseMapper |
| work_order | WorkOrder | IWorkOrderRepository | WorkOrderDtoMapper | WorkOrderJpaEntity | WorkOrderMapper |

## New Feature Checklist (Clean Architecture + CQRS)
1. **Domain**: Create `XxxEntity.java` in `domain/{module}/model/`, interface `IXxxRepository` in `domain/{module}/repository/`
2. **Application**: `CreateXxxCommand.java` + `UpdateXxxCommand.java` in `application/{module}/command/`; `GetXxxByIdQuery.java` + `ListXxxQuery.java` in `application/{module}/query/`; handlers in `application/{module}/handler/`; `XxxDto.java` in `application/{module}/dto/`; `XxxDtoMapper.java` in `application/{module}/mapper/`
3. **Infrastructure**: `XxxJpaEntity.java` in `infrastructure/persistence/entity/`; `XxxJpaRepository` in `infrastructure/persistence/jpa/`; `XxxMapper.java` in `infrastructure/persistence/mapper/`; `XxxRepositoryImpl.java` in `infrastructure/persistence/repository/`
4. **Presentation**: `XxxController.java` in `presentation/api/controller/`; add path constant to `Constants.java`
5. **Migration**: New Flyway script `V{n}__description.sql` in `src/main/resources/db/migration/`

## Known Gaps / Notes
- `ChatController.sendMessage` returns `ApiResponse<UUID>` (no GetMessageByIdQueryHandler exists — intentional, messages are fetched via list)
- `UserDtoMapper` does NOT exist (user module only); `GetUserByIdQueryHandler` has inline mapping with roles
- `role` module: domain entity exists, IRoleRepository exists, but no dedicated application CQRS handlers (role assignment handled via User)
- `asset_status_history` module: append-only (no update/delete), managed via SetAssetStatusCommandHandler
