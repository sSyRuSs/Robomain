CREATE TABLE IF NOT EXISTS preventive_maintenance_schedule (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    asset_id UUID NOT NULL,
    maintenance_id UUID NOT NULL,
    schedule_name VARCHAR(255) NOT NULL,
    description TEXT,
    frequency VARCHAR(50) NOT NULL,
    interval_count INTEGER NOT NULL DEFAULT 1,
    next_due_date TIMESTAMP NOT NULL,
    last_generated_at TIMESTAMP,
    estimated_duration_hours INTEGER NOT NULL DEFAULT 2,
    lead_time_days INTEGER NOT NULL DEFAULT 0,
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_pm_schedule_enterprise FOREIGN KEY (enterprise_id)
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT fk_pm_schedule_asset FOREIGN KEY (asset_id)
        REFERENCES asset(id) ON DELETE CASCADE,
    CONSTRAINT fk_pm_schedule_maintenance FOREIGN KEY (maintenance_id)
        REFERENCES maintenance(id) ON DELETE RESTRICT,
    CONSTRAINT ck_pm_schedule_interval_positive CHECK (interval_count > 0),
    CONSTRAINT ck_pm_schedule_duration_positive CHECK (estimated_duration_hours > 0),
    CONSTRAINT ck_pm_schedule_lead_time_non_negative CHECK (lead_time_days >= 0),
    CONSTRAINT ck_pm_schedule_frequency CHECK (frequency IN ('DAILY', 'WEEKLY', 'MONTHLY', 'QUARTERLY', 'YEARLY')),
    CONSTRAINT ck_pm_schedule_status CHECK (status IN ('ACTIVE', 'PAUSED', 'COMPLETED'))
);

CREATE INDEX IF NOT EXISTS idx_pm_schedule_enterprise ON preventive_maintenance_schedule(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_pm_schedule_asset ON preventive_maintenance_schedule(asset_id);
CREATE INDEX IF NOT EXISTS idx_pm_schedule_next_due ON preventive_maintenance_schedule(next_due_date);
CREATE INDEX IF NOT EXISTS idx_pm_schedule_status ON preventive_maintenance_schedule(status);

CREATE TABLE IF NOT EXISTS spare_part_reservation (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    inventory_id UUID NOT NULL,
    work_order_id UUID NOT NULL,
    task_id UUID,
    quantity INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL DEFAULT 'RESERVED',
    reserved_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    issued_at TIMESTAMP,
    cancelled_at TIMESTAMP,
    note VARCHAR(500),
    reserved_by UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_spare_reservation_enterprise FOREIGN KEY (enterprise_id)
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT fk_spare_reservation_inventory FOREIGN KEY (inventory_id)
        REFERENCES inventory(id) ON DELETE RESTRICT,
    CONSTRAINT fk_spare_reservation_work_order FOREIGN KEY (work_order_id)
        REFERENCES work_order(id) ON DELETE CASCADE,
    CONSTRAINT fk_spare_reservation_task FOREIGN KEY (task_id)
        REFERENCES task(id) ON DELETE SET NULL,
    CONSTRAINT fk_spare_reservation_user FOREIGN KEY (reserved_by)
        REFERENCES account(id) ON DELETE SET NULL,
    CONSTRAINT ck_spare_reservation_quantity_positive CHECK (quantity > 0),
    CONSTRAINT ck_spare_reservation_status CHECK (status IN ('RESERVED', 'ISSUED', 'CANCELLED'))
);

CREATE INDEX IF NOT EXISTS idx_spare_reservation_enterprise ON spare_part_reservation(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_spare_reservation_inventory ON spare_part_reservation(inventory_id);
CREATE INDEX IF NOT EXISTS idx_spare_reservation_work_order ON spare_part_reservation(work_order_id);
CREATE INDEX IF NOT EXISTS idx_spare_reservation_task ON spare_part_reservation(task_id);
CREATE INDEX IF NOT EXISTS idx_spare_reservation_status ON spare_part_reservation(status);
