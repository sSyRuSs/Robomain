CREATE TYPE EnumStatus AS ENUM (
      'In Open',
      'Complete',
      'In Progress',
      'Overdue'
);

CREATE TYPE EnumPriority AS ENUM (
      'Low',
      'Medium',
      'High'
);

CREATE TABLE IF NOT EXISTS task(
    id UUID PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    task_description VARCHAR(255),
    task_location VARCHAR(255),
    task_status EnumStatus NOT NULL,
    task_priority EnumPriority NOT NULL,
    task_suggest_start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    task_suggest_end_date TIMESTAMP,
    task_start_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    task_end_date TIMESTAMP,
    task_star BOOLEAN default false,
    user_id UUID,
    maintenance_id UUID,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account (id) ON DELETE SET NULL,
    FOREIGN KEY (maintenance_id) REFERENCES maintenance (id) ON DELETE SET NULL
);