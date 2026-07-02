alter TABLE facility
    DROP COLUMN m_asset_category_id CASCADE;


ALTER TABLE facility
    ADD COLUMN asset_category_id UUID null,
    ADD FOREIGN KEY (asset_category_id) REFERENCES asset_category (id) ON DELETE SET NULL;