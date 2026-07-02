create type EnumAsetStatus as ENUM ('ONLINE', 'OFFLINE');

create TABLE IF NOT EXISTS asset_status (
    id UUID PRIMARY KEY,
    asset_id UUID,
    status_enum EnumAsetStatus NOT NULL,
    reason TEXT,
    from_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (asset_id) REFERENCES asset(id) on DELETE set NULL
);