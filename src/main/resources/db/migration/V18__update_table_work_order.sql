drop TABLE work_order CASCADE;

create TABLE IF NOT EXISTS work_order (
    id UUID PRIMARY KEY,
    work_order_name VARCHAR(255) NOT NULL,
    work_order_description TEXT,
    work_order_location VARCHAR(255),
    work_order_status EnumStatus NOT NULL,
    work_order_priority EnumPriority NOT NULL,
    work_order_suggest_start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    work_order_suggest_end_date TIMESTAMP,
    work_order_start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    work_order_end_date TIMESTAMP,
    work_order_star BOOLEAN default false,
    user_id UUID,
    maintenance_id UUID,
    asset_id UUID,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account (id) ON DELETE SET NULL,
    FOREIGN KEY (maintenance_id) REFERENCES maintenance (id) ON DELETE SET NULL,
    FOREIGN KEY (asset_id) REFERENCES asset (id) ON DELETE SET NULL
);