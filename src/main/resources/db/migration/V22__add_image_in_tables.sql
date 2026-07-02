alter TAble equipment
add column equipment_image varchar(255);

alter TABLE tool
add column tool_image varchar(255);

alter TABLE facility_location
add COLUMN location_image varchar(255);

ALTER TABLE task_detail
add COLUMN task_detail_image varchar(255)[];