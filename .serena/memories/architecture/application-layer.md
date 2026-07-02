# Application Layer — Complete Symbol Map

## Package pattern: `application/{module}/`
Each module contains: `command/`, `query/`, `handler/`, `dto/`, `mapper/`
Some have: `validator/`

## Modules & Commands
| Module | Commands |
|---|---|
| asset | CreateAssetCommand, UpdateAssetCommand |
| asset_category | CreateAssetCategoryCommand, UpdateAssetCategoryCommand |
| asset_status | SetAssetStatusCommand |
| auth | LoginCommand, LogoutCommand, RefreshTokenCommand |
| bill | CreateBillCommand, UpdateBillCommand |
| bill_category | CreateBillCategoryCommand, UpdateBillCategoryCommand |
| chat | CreateConversationCommand, SendMessageCommand |
| department | CreateDepartmentCommand, UpdateDepartmentCommand |
| enterprise | CreateEnterpriseCommand, UpdateEnterpriseCommand |
| facility | CreateFacilityCommand, UpdateFacilityCommand |
| inventory | CreateInventoryCommand, UpdateInventoryCommand |
| inventory_category | CreateInventoryCategoryCommand, UpdateInventoryCategoryCommand |
| maintenance | CreateMaintenanceCommand, UpdateMaintenanceCommand |
| notification | CreateNotificationCommand, MarkNotificationAsReadCommand, MarkAllNotificationsAsReadCommand |
| preventive_maintenance | CreatePreventiveMaintenanceScheduleCommand, UpdatePreventiveMaintenanceScheduleCommand |
| purchase_request | CreatePurchaseRequestCommand, ApprovePurchaseRequestCommand, RejectPurchaseRequestCommand |
| response_issue | CreateResponseIssueCommand, UpdateResponseIssueCommand |
| spare_part_reservation | CreateSparePartReservationCommand, IssueSparePartReservationCommand, CancelSparePartReservationCommand |
| stock_movement | RecordStockMovementCommand |
| task | CreateTaskCommand, UpdateTaskCommand |
| task_detail | CreateTaskDetailCommand, UpdateTaskDetailCommand, CompleteTaskDetailCommand |
| user | CreateUserCommand, UpdateUserCommand |
| vendor | CreateVendorCommand, UpdateVendorCommand |
| warehouse | CreateWarehouseCommand, UpdateWarehouseCommand |
| work_order | CreateWorkOrderCommand, UpdateWorkOrderCommand |

## Modules & Queries
| Module | Queries |
|---|---|
| asset | GetAssetByIdQuery, ListAssetsQuery |
| asset_category | GetAssetCategoryByIdQuery, ListAssetCategoriesQuery |
| asset_status | GetAssetStatusQuery, ListAssetStatusHistoryQuery |
| bill | GetBillByIdQuery, ListBillsByTaskQuery |
| bill_category | GetBillCategoryByIdQuery, ListBillCategoriesQuery |
| chat | GetConversationByIdQuery, ListMessagesQuery |
| department | GetDepartmentByIdQuery, ListDepartmentsQuery |
| enterprise | GetEnterpriseByIdQuery, ListEnterprisesQuery |
| facility | GetFacilityByIdQuery, ListFacilitiesQuery |
| inventory | GetInventoryByIdQuery, ListInventoryQuery |
| inventory_category | GetInventoryCategoryByIdQuery, ListInventoryCategoriesQuery |
| maintenance | GetMaintenanceByIdQuery, ListMaintenancesQuery |
| notification | GetNotificationByIdQuery, ListNotificationsQuery |
| preventive_maintenance | GetPreventiveMaintenanceScheduleByIdQuery, ListPreventiveMaintenanceSchedulesQuery |
| purchase_request | GetPurchaseRequestByIdQuery, ListPurchaseRequestsQuery |
| response_issue | GetResponseIssueByIdQuery, ListResponseIssuesByIssueQuery |
| spare_part_reservation | GetSparePartReservationByIdQuery, ListSparePartReservationsQuery |
| stock_movement | GetStockMovementByIdQuery, ListStockMovementsQuery |
| task | GetTaskByIdQuery, ListTasksQuery |
| task_detail | GetTaskDetailByIdQuery, ListTaskDetailsQuery |
| user | GetUserByIdQuery, ListUsersQuery |
| vendor | GetVendorByIdQuery, ListVendorsQuery |
| warehouse | GetWarehouseByIdQuery, ListWarehousesQuery |
| work_order | GetWorkOrderByIdQuery, ListWorkOrdersQuery |

## CQRS Handler Convention
- **Command handlers** (all return `UUID`): `CreateXxxCommandHandler`, `UpdateXxxCommandHandler`, `SetXxxCommandHandler`, `RecordXxxCommandHandler`, `ApprovePurchaseRequestCommandHandler`, `RejectPurchaseRequestCommandHandler`, `IssueSparePartReservationCommandHandler`, `CancelSparePartReservationCommandHandler`, `CompleteTaskDetailCommandHandler`, `MarkNotificationAsReadCommandHandler`, `MarkAllNotificationsAsReadCommandHandler`
- **Auth handlers**: `LoginCommandHandler`, `LogoutCommandHandler` (returns void), `RefreshTokenCommandHandler`
- **Query handlers** (all annotated `@Transactional(readOnly=true)`): `GetXxxByIdQueryHandler`, `ListXxxQueryHandler`

## Application DtoMappers (application/{module}/mapper/)
One `@Component` mapper per module, method `public XxxDto toDto(Xxx entity)`:
AssetDtoMapper, AssetCategoryDtoMapper, AssetStatusDtoMapper,
BillDtoMapper, BillCategoryDtoMapper, ChatConversationDtoMapper, ChatMessageDtoMapper,
DepartmentDtoMapper, EnterpriseDtoMapper, FacilityDtoMapper,
InventoryDtoMapper, InventoryCategoryDtoMapper, MaintenanceDtoMapper,
NotificationDtoMapper, PreventiveMaintenanceScheduleDtoMapper, PurchaseRequestDtoMapper,
ResponseIssueDtoMapper, SparePartReservationDtoMapper, StockMovementDtoMapper,
TaskDtoMapper, TaskDetailDtoMapper, VendorDtoMapper, WarehouseDtoMapper, WorkOrderDtoMapper

## Shared Application
- `application/shared/Constants.java` — all API path constants (ASSET_PATH, FACILITY_PATH, etc.)
- `application/shared/response/ApiResponse<T>` — standard response wrapper
- `application/shared/response/PaginationResponse<T>` — paginated list response
