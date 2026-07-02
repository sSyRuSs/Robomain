CREATE TABLE IF NOT EXISTS facility_location(
    id UUID PRIMARY KEY,
    location_name VARCHAR(255) NOT NULL,
    location_description VARCHAR(255),
    location_problem VARCHAR(255),
    equipment_id UUID,
    tool_id UUID,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP,
    FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON DELETE SET NULL,
    FOREIGN KEY (tool_id) REFERENCES tool (id) ON DELETE SET NULL
)