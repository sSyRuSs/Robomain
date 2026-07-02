package com.example.Robomain.application.shared;

/**
 * Central registry of all API path constants.
 * Keeps route definitions in one place to avoid duplication.
 */
public final class Constants {

    private Constants() {}

    // Auth
    public static final String AUTH_PATH = "/api/v1/auth";

    // User & Role
    public static final String USER_PATH = "/api/v1/user";
    public static final String ROLE_PATH = "/api/v1/role";
    public static final String USER_ROLE_PATH = "/api/v1/user_role";

    // Organization
    public static final String ENTERPRISE_PATH = "/api/v1/enterprise";
    public static final String DEPARTMENT_PATH = "/api/v1/department";
    public static final String FACILITY_PATH = "/api/v1/facility";

    // Asset
    public static final String ASSET_PATH = "/api/v1/asset";
    public static final String ASSET_CATEGORY_PATH = "/api/v1/asset_category";
    public static final String ASSET_STATUS_PATH = "/api/v1/asset_status";

    // Maintenance & Work Order
    public static final String MAINTENANCE_PATH = "/api/v1/maintenance";
    public static final String WORK_ORDER_PATH = "/api/v1/work_order";
    public static final String TASK_PATH = "/api/v1/task";
    public static final String TASK_DETAIL_PATH = "/api/v1/task_detail";

    // Inventory
    public static final String INVENTORY_PATH = "/api/v1/inventory";
    public static final String INVENTORY_CATEGORY_PATH = "/api/v1/inventory_category";
    public static final String WAREHOUSE_PATH = "/api/v1/warehouse";

    // Finance
    public static final String BILL_PATH = "/api/v1/bill";
    public static final String BILL_CATEGORY_PATH = "/api/v1/bill_category";
    public static final String PURCHASE_REQUEST_PATH = "/api/v1/purchase_request";
    public static final String VENDOR_PATH = "/api/v1/vendor";

    // Features
    public static final String NOTIFICATION_PATH = "/api/v1/notification";
    public static final String CHAT_PATH = "/api/v1/chat";
    public static final String RESPONSE_ISSUE_PATH = "/api/v1/response-issue";
    public static final String BACKUP_PATH = "/api/v1/backup";
    public static final String PREVENTIVE_MAINTENANCE_PATH = "/api/v1/preventive-maintenance-schedules";
    public static final String SPARE_PART_RESERVATION_PATH = "/api/v1/spare-part-reservations";
    public static final String WEB_RTC_PATH = "/api/v1/webRTC";

    // Pagination
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final int MAX_PAGE_SIZE = 100;
}
