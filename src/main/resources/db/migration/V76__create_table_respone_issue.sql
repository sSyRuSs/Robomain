CREATE TABLE IF NOT EXISTS response_issue (
    id UUID PRIMARY KEY,
    response_id UUID,
    issue_id UUID,
    response_title VARCHAR(255),
    response_description TEXT,
    file_info JSONB,
    important BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (response_id) REFERENCES response_issue(id) on DELETE CASCADE,
    FOREIGN KEY (issue_id) REFERENCES task_detail(id) on DELETE CASCADE
);