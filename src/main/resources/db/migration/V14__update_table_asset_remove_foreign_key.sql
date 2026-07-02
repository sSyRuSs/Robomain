drop TABLE asset CASCADE;

CREATE TABLE IF NOT EXISTS asset (
    id UUID PRIMARY KEY,
    asset_name VARCHAR(255) NOT NULL,
    asset_description TEXT,
    asset_problen VARCHAR(255),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP
);