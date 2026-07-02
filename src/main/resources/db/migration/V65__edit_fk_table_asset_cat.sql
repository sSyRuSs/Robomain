alter TABLE asset_category
    drop CONSTRAINT asset_category_m_asset_category_id_fkey CASCADE;

ALTER TABLE asset_category
    add FOREIGN KEY (m_asset_category_id) REFERENCES asset_category(id) ON DELETE set null;