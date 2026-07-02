alter TABLE asset
    ADD COLUMN location_id UUID,
    add COLUMN equipment_id UUID,
    add COLUMN tool_id UUID,
    add CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES facility_location (id) ON DELETE SET NULL,
    add CONSTRAINT fk_equipment_id FOREIGN KEY (equipment_id) REFERENCES equipment (id) ON DELETE SET NULL,
    add CONSTRAINT fk_tool_id FOREIGN KEY (tool_id) REFERENCES tool (id) ON DELETE SET NULL;