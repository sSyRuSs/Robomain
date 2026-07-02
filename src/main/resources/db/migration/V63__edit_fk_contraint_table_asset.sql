alter TABLE asset
    drop CONSTRAINT asset_asset_category_id_fkey CASCADE;

ALTER TABLE asset
    add FOREIGN KEY (asset_category_id) REFERENCES asset_category(id) ON DELETE set null;