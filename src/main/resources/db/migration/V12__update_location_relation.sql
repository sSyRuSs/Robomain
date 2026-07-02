drop TABLE IF EXISTS facility_location cascade;

CREATE TABLE IF NOT EXISTS facility_location(
    id UUID PRIMARY KEY,
    location_name VARCHAR(255) NOT NULL,
    location_description VARCHAR(255),
    location_problem VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP
);

alter TABLE equipment
    ADD COLUMN location_id UUID,
    ADD CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES facility_location (id) ON DELETE SET NULL;

alter TABLE tool
    ADD COLUMN location_id UUID,
    ADD CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES facility_location (id) ON DELETE SET NULL;