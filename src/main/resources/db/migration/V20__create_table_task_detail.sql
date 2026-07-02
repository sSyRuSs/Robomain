CREATE TABLE IF NOT EXISTS task_detail (
    id UUID PRIMARY KEY,
    task_id UUID,
    task_detail_description TEXT,
    task_detail_problem TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES task(id) on DELETE set null
);