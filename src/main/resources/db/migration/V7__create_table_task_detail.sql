CREATE TABLE IF NOT EXISTS task_detail(
    id UUID PRIMARY KEY,
    task_id UUID,
    task_detail_name VARCHAR(255) NOT NULL,
    task_detail_description VARCHAR(255),
    task_detail_problem VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES task (id) ON DELETE SET NULL
);
