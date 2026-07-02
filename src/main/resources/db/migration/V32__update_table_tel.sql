ALTER TABLE if EXISTS tool
DROP COLUMN IF EXISTS tool_image;

ALTER TABLE if EXISTS equipment
DROP COLUMN IF EXISTS equipment_image;

ALTER TABLE if EXISTS facility_location
DROP COLUMN IF EXISTS location_image;
