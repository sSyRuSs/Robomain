# Presentation Layer — REST Controllers Map

## Package: `presentation/api/controller/`

| Controller | Base Path (from Constants) | Handlers injected | Endpoints |
|---|---|---|---|
| AssetController | ASSET_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| AssetCategoryController | ASSET_CATEGORY_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| AssetStatusController | ASSET_STATUS_PATH | set, getStatus, listHistory | POST/PUT (SetStatus), GET, GET /history |
| AuthController | AUTH_PATH | login, logout, refreshToken | POST /login, POST /logout, POST /refresh |
| BillController | BILL_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| BillCategoryController | BILL_CATEGORY_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| ChatController | CHAT_PATH | createConversation, getConversation, sendMessage, listMessages | POST /conversations, GET /conversations/{id}, POST /conversations/{id}/messages → UUID, GET /conversations/{id}/messages |
| DepartmentController | DEPARTMENT_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| EnterpriseController | ENTERPRISE_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| FacilityController | FACILITY_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| InventoryController | INVENTORY_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| InventoryCategoryController | INVENTORY_CATEGORY_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| MaintenanceController | MAINTENANCE_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| NotificationController | NOTIFICATION_PATH | create, markAsRead, markAllAsRead, getById, list | POST, PATCH /{id}/read, PATCH /read-all, GET /{id}, GET |
| PreventiveMaintenanceController | PREVENTIVE_MAINTENANCE_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| PurchaseRequestController | PURCHASE_REQUEST_PATH | create, approve, reject, getById, list | POST, PATCH /{id}/approve, PATCH /{id}/reject, GET /{id}, GET |
| ResponseIssueController | RESPONSE_ISSUE_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| SparePartReservationController | SPARE_PART_RESERVATION_PATH | create, issue, cancel, getById, list | POST, PATCH /{id}/issue, PATCH /{id}/cancel, GET /{id}, GET |
| StockMovementController | INVENTORY_PATH + /movements | record, getById, list | POST, GET /{id}, GET |
| TaskController | TASK_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| TaskDetailController | TASK_DETAIL_PATH | create, update, complete, getById, list | POST, PUT /{id}, PATCH /{id}/complete, GET /{id}, GET |
| UserController | USER_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| VendorController | VENDOR_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| WarehouseController | WAREHOUSE_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |
| WorkOrderController | WORK_ORDER_PATH | create, update, getById, list | POST, PUT /{id}, GET /{id}, GET |

## Controller CQRS Pattern (all create/update/action endpoints)
```java
// POST (create)
UUID id = createHandler.handle(cmd);
return ResponseEntity.status(201).body(ApiResponse.success(getByIdHandler.handle(new GetXxxByIdQuery(id))));

// PUT (update)
UUID updatedId = updateHandler.handle(cmd);
return ResponseEntity.ok(ApiResponse.success(getByIdHandler.handle(new GetXxxByIdQuery(updatedId))));

// EXCEPTION: ChatController.sendMessage → returns ApiResponse<UUID> (no GetMessageById handler exists)
```

## Presentation Config
- `presentation/api/exception/GlobalExceptionHandler` — handles DomainException hierarchy → proper HTTP codes
- `presentation/config/SwaggerConfig` — SpringDoc OpenAPI configuration (springdoc 3.0.0)

## Security — Public Endpoints
Defined in **both** `WebSecurityConfig.requestMatchers(...).permitAll()` AND `AuthTokenFilter.isPublicPath()`:
- `/api/v1/auth/login`, `/api/v1/auth/refresh_token`
- `/swagger-ui/**`, `/api-docs/**`, `/actuator/health`
- `POST /api/v1/user` — temporary permit for first-user bootstrap (restrict after setup)

## Response Wrappers
All endpoints return:
- `ResponseEntity<ApiResponse<XxxDto>>` for single items
- `ResponseEntity<ApiResponse<PaginationResponse<XxxDto>>>` for lists
- `ResponseEntity<ApiResponse<UUID>>` for ChatController.sendMessage
- `ResponseEntity<ApiResponse<Void>>` for fire-and-forget (MarkAllAsRead)
