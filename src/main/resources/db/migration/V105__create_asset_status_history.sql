create TABLE IF NOT EXISTS asset_status_history (
    id UUID PRIMARY KEY,
    asset_id UUID,
    asset_status_id UUID,
    status_enum EnumAsetStatus NOT NULL,
    reason TEXT,
    changed_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (asset_id) REFERENCES asset(id) on DELETE set NULL,
    FOREIGN KEY (asset_status_id) REFERENCES asset_status(id) on DELETE set NULL
);

create INDEX IF NOT EXISTS idx_asset_status_history_asset_id_changed_at
    ON asset_status_history(asset_id, changed_at DESC);
