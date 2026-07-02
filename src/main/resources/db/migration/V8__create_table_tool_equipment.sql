create TABLE IF NOT EXISTS tool(
    id UUID PRIMARY KEY,
    tool_name VARCHAR(255) NOT NULL,
    tool_description VARCHAR(255),
    tool_problem VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS equipment(
    id UUID PRIMARY KEY,
    equipment_name VARCHAR(255) NOT NULL,
    equipment_description VARCHAR(255),
    equipment_problem VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
)