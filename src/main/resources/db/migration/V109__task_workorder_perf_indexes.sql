-- V109: Performance indexes for Task and WorkOrder modules
CREATE EXTENSION IF NOT EXISTS pg_trgm;

CREATE INDEX IF NOT EXISTS idx_task_name_trgm
    ON task USING gin(task_name gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_work_order_name_trgm
    ON work_order USING gin(work_order_name gin_trgm_ops);

CREATE INDEX IF NOT EXISTS idx_task_detail_task_id_created_at
    ON task_detail(task_id, created_at DESC);
