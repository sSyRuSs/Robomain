alter TABLE asset
add column asset_code VARCHAR(255),
add column asset_quantity INT NOT NULL DEFAULT 0,
add COLUMN IF NOT EXISTS asset_category_id UUID, 
add column if not exists file_info JSONB,
add FOREIGN KEY (asset_category_id) REFERENCES asset_category(id);

ALTER TABLE asset
DROP COLUMN asset_problen CASCADE;