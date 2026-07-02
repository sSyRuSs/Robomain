CREATE TABLE IF NOT EXISTS work_order (
    id UUID PRIMARY KEY,
    wo_name VARCHAR(255) NOT NULL,
    wo_order_description TEXT,
    wo_location VARCHAR(255),
    wo_status EnumStatus NOT NULL,
    wo_priority EnumPriority NOT NULL,
    wo_suggest_start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    wo_suggest_end_date TIMESTAMP,
    wo_start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    wo_end_date TIMESTAMP,
    wo_star BOOLEAN default false,
    user_id UUID,
    maintenance_id UUID,
    asset_id UUID,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account (id) ON DELETE SET NULL,
    FOREIGN KEY (maintenance_id) REFERENCES maintenance (id) ON DELETE SET NULL,
    FOREIGN KEY (asset_id) REFERENCES asset (id) ON DELETE SET NULL
);

alter TABLE task
add column work_order_id UUID,
add FOREIGN KEY (work_order_id) REFERENCES work_order (id) ON DELETE SET NULL;