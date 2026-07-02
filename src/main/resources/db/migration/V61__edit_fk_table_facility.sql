ALTER TABLE facility
    ADD COLUMN m_asset_category_id UUID,
    ADD FOREIGN KEY (m_asset_category_id) REFERENCES asset (id);