create TABLE IF NOT EXISTS asset_category (
    id UUID PRIMARY KEY,
    asset_category_name VARCHAR(255) NOT NULL,
    asset_category_quantity INT NOT NULL DEFAULT 0,
    asset_category_description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);