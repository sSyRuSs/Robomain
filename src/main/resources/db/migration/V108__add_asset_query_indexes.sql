CREATE INDEX IF NOT EXISTS idx_asset_facility_created_at ON asset(facility_id, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_asset_category_created_at ON asset(asset_category_id, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_asset_parent_created_at ON asset(m_asset_id, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_asset_lower_name ON asset(LOWER(asset_name));
CREATE INDEX IF NOT EXISTS idx_asset_status_asset_id ON asset_status(asset_id);
