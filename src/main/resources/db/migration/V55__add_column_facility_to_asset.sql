alter TABLE asset
add COLUMN IF NOT EXISTS facility_id UUID,
add FOREIGN KEY (facility_id) REFERENCES facility(id);