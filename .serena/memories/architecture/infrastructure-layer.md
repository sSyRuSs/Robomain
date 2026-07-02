# Infrastructure Layer — Complete Symbol Map

## Infrastructure Package Structure
```
infrastructure/
  config/           → ModelMapperConfig
  persistence/
    base/           → BaseJpaEntity (@MappedSuperclass: UUID id, Date createdAt/updatedAt)
    entity/         → JPA entities (XxxJpaEntity extends BaseJpaEntity)
    jpa/            → Spring Data JPA interfaces (XxxJpaRepository extends JpaRepository)
    mapper/         → Infra mappers (XxxMapper: toDomain(), toJpa())
    repository/     → Impl classes (XxxRepositoryImpl implements IXxxRepository)
  security/         → JWT filter, security config
```

## JPA Entities (infrastructure/persistence/entity/)
All extend `BaseJpaEntity`, annotated with `@Entity @Table`, Lombok `@Getter @Setter @Builder`:
AssetJpaEntity, AssetCategoryJpaEntity, AssetStatusJpaEntity, AssetStatusHistoryJpaEntity,
BillJpaEntity, BillCategoryJpaEntity,
ChatConversationJpaEntity, ChatMessageJpaEntity,
DepartmentJpaEntity, EnterpriseJpaEntity, FacilityJpaEntity,
InventoryJpaEntity, InventoryCategoryJpaEntity,
MaintenanceJpaEntity, NotificationJpaEntity,
PreventiveMaintenanceScheduleJpaEntity, PurchaseRequestJpaEntity,
ResponseIssueJpaEntity, RoleJpaEntity, SparePartReservationJpaEntity,
StockMovementJpaEntity, TaskJpaEntity, TaskDetailJpaEntity,
UserJpaEntity (`account` table), UserRoleJpaEntity (`account_role` table, UserRoleId composite key), UserTokenJpaEntity,
VendorJpaEntity, WarehouseJpaEntity, WorkOrderJpaEntity

## Infrastructure Mappers (infrastructure/persistence/mapper/)
Each `@Component`, methods `toDomain(JpaEntity e)` and `toJpa(Domain d)`:
AssetMapper, AssetCategoryMapper, AssetStatusMapper, AssetStatusHistoryMapper,
BillMapper, BillCategoryMapper, ChatConversationMapper, ChatMessageMapper,
DepartmentMapper, EnterpriseMapper, FacilityMapper,
InventoryMapper, InventoryCategoryMapper, MaintenanceMapper, NotificationMapper,
PreventiveMaintenanceScheduleMapper, PurchaseRequestMapper, ResponseIssueMapper,
SparePartReservationMapper, StockMovementMapper, TaskMapper, TaskDetailMapper,
UserMapper, UserTokenMapper, VendorMapper, WarehouseMapper, WorkOrderMapper

## Repository Implementations (infrastructure/persistence/repository/)
Each implements the corresponding domain interface, injects JpaRepository + Mapper:
AssetRepositoryImpl, AssetCategoryRepositoryImpl, AssetStatusRepositoryImpl,
AssetStatusHistoryRepositoryImpl, BillRepositoryImpl, BillCategoryRepositoryImpl,
ChatConversationRepositoryImpl, ChatMessageRepositoryImpl,
DepartmentRepositoryImpl, EnterpriseRepositoryImpl, FacilityRepositoryImpl,
InventoryRepositoryImpl, InventoryCategoryRepositoryImpl, MaintenanceRepositoryImpl,
NotificationRepositoryImpl, PreventiveMaintenanceScheduleRepositoryImpl,
PurchaseRequestRepositoryImpl, ResponseIssueRepositoryImpl, RoleRepositoryImpl,
SparePartReservationRepositoryImpl, StockMovementRepositoryImpl,
TaskRepositoryImpl, TaskDetailRepositoryImpl, UserRepositoryImpl,
UserTokenRepositoryImpl, VendorRepositoryImpl, WarehouseRepositoryImpl, WorkOrderRepositoryImpl

## Database Migrations
Flyway, 108 versions (V1–V108), location: `src/main/resources/db/migration/`
Key tables: facility, role, account, asset, maintenance, work_order, task, task_detail,
bill, bill_category, surcharge, inventory, warehouse, vendor, purchase_request,
asset_status_history, preventive_maintenance, spare_part_reservation, chat (direct + rooms)
