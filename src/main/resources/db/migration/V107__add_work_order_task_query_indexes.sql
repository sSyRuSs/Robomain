CREATE INDEX IF NOT EXISTS idx_task_work_order_created_at
    ON task(work_order_id, created_at DESC);

CREATE INDEX IF NOT EXISTS idx_task_user_created_at
    ON task(user_id, created_at DESC);

CREATE INDEX IF NOT EXISTS idx_task_status_priority_created_at
    ON task(task_status, task_priority, created_at DESC);

CREATE INDEX IF NOT EXISTS idx_task_asset_id
    ON task(asset_id);

CREATE INDEX IF NOT EXISTS idx_work_order_status_priority_created_at
    ON work_order(work_order_status, work_order_priority, created_at DESC);

CREATE INDEX IF NOT EXISTS idx_work_order_asset_id
    ON work_order(asset_id);

CREATE INDEX IF NOT EXISTS idx_work_order_maintenance_id
    ON work_order(maintenance_id);
