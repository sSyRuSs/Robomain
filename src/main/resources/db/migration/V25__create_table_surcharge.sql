CREATE TABLE IF NOT EXISTS surcharge(
    id UUID PRIMARY KEY,
    surcharge_name VARCHAR(255) NOT NULL,
    surcharge_unit VARCHAR(255) NOT NULL,
    surcharge_price DECIMAL(10, 2),
    bill_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bill_id) REFERENCES bill(id) on DELETE CASCADE
);