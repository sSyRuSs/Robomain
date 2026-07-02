create TABLE IF NOT EXISTS facility(
    id UUID PRIMARY KEY,
    facility_name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP
);