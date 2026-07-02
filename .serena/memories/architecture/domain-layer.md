# Domain Layer — Complete Symbol Map

## Base / Shared
- `domain/shared/exception/DomainException` (base RuntimeException)
  - `ConflictException` (409)
  - `ResourceNotFoundException` (404)
  - `ValidationException` (400)
- `domain/auth/exception/AuthenticationFailedException`

## Enums (domain/shared/enums)
| Enum | Values (approx) |
|---|---|
| EnumAssetStatus | ACTIVE, INACTIVE, ... |
| EnumInventoryStatus | AVAILABLE, LOW_STOCK, OUT_OF_STOCK, RESERVED, DISCONTINUED |
| EnumMaintenanceFrequency | DAILY, WEEKLY, MONTHLY, QUARTERLY, ANNUALLY, CUSTOM |
| EnumMovementType | IN, OUT, TRANSFER, ADJUSTMENT, RETURN, RESERVED |
| EnumPreventiveScheduleStatus | PENDING, COMPLETED, OVERDUE, CANCELLED, IN_PROGRESS |
| EnumPriority | LOW, MEDIUM, HIGH, CRITICAL |
| EnumPRStatus | PENDING, APPROVED, REJECTED, CANCELLED, COMPLETED |
| EnumPRType | PURCHASE, INTERNAL |
| EnumRole | ADMIN, MANAGER, TECHNICIAN, VIEWER, ... |
| EnumSparePartReservationStatus | RESERVED, ISSUED, CANCELLED |
| EnumStatus | PENDING, IN_PROGRESS, COMPLETED, CANCELLED, ON_HOLD, OVERDUE, REJECTED |
| EnumVendorStatus | ACTIVE, INACTIVE, BLACKLISTED |
| EnumWarehouseType | MAIN, SECONDARY, TRANSIT |

## Domain Entities (all Pure Java, Lombok @Builder)
| Module | Entity | Key methods |
|---|---|---|
| asset | Asset | create(), update(), setParent(), getParentAssetId() |
| asset_category | AssetCategory | create(), update(), setParent() |
| asset_status | AssetStatus | create(), update() |
| asset_status_history | AssetStatusHistory | record() |
| bill | Bill | (from domain/bill/model/) |
| bill_category | BillCategory | create(), update() |
| chat | ChatConversation | create(), isCanSendMessages() |
| chat | ChatMessage | send() |
| department | Department | create(), update() |
| enterprise | Enterprise | (from domain/enterprise/model/) |
| facility | Facility | create(), update(), setParent() |
| inventory | Inventory | (from domain/inventory/model/) |
| inventory_category | InventoryCategory | create(), update(), setParent() |
| maintenance | Maintenance | create(), update() |
| notification | Notification | create(), markAsRead() |
| preventive_maintenance | PreventiveMaintenanceSchedule | create(), update(), complete(), cancel() |
| purchase_request | PurchaseRequest | create(), approve(), reject() |
| response_issue | ResponseIssue | (from domain/response_issue/model/) |
| spare_part_reservation | SparePartReservation | create(), issue(), cancel() |
| stock_movement | StockMovement | (via RecordStockMovement) |
| task | Task | create(), update(), complete(), etc. |
| task_detail | TaskDetail | create(), update(), complete() |
| user | User | create(), updateProfile() |
| vendor | Vendor | (from domain/vendor/model/) |
| warehouse | Warehouse | (from domain/warehouse/model/) |
| work_order | WorkOrder | create(), update() |

## Domain Repository Interfaces (domain/xxx/repository/)
IAssetRepository, IAssetCategoryRepository, IAssetStatusRepository,
IAssetStatusHistoryRepository, IUserTokenRepository, IBillRepository,
IBillCategoryRepository, IChatMessageRepository, IConversationRepository,
IDepartmentRepository, IEnterpriseRepository, IFacilityRepository,
IInventoryRepository, IInventoryCategoryRepository, IMaintenanceRepository,
INotificationRepository, IPreventiveMaintenanceScheduleRepository,
IPurchaseRequestRepository, IResponseIssueRepository, IRoleRepository,
ISparePartReservationRepository, IStockMovementRepository, ITaskRepository,
ITaskDetailRepository, IUserRepository, IVendorRepository,
IWarehouseRepository, IWorkOrderRepository

## Domain Events
| Event | Module |
|---|---|
| AssetCreatedEvent | asset |
| EnterpriseCreatedEvent | enterprise |
| FacilityCreatedEvent | facility |
| InventoryCreatedEvent | inventory |
| PurchaseRequestCreatedEvent | purchase_request |
| PurchaseRequestApprovedEvent | purchase_request |
| StockLevelChangedEvent | stock_movement |
| TaskCreatedEvent | task |
| UserCreatedEvent | user |
