CREATE TABLE IF NOT EXISTS asset(
    id UUID PRIMARY KEY,
    asset_name VARCHAR(255) NOT NULL,
    asset_description VARCHAR(255),
    asset_problem VARCHAR(255),
    location_id UUID,
    tool_id UUID,
    equipment_id UUID,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP,
    FOREIGN KEY (location_id) REFERENCES facility_location (id) ON DELETE SET NULL,
    FOREIGN KEY (tool_id) REFERENCES tool (id) ON DELETE SET NULL,
    FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON DELETE SET NULL
);

ALTER TABLE task 
ADD COLUMN asset_id UUID,
add FOREIGN KEY (asset_id) REFERENCES asset (id) ON DELETE SET NULL;
