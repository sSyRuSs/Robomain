create TABLE IF NOT EXISTS bill_category(
    id UUID PRIMARY KEY,
    bill_id UUID,
    bill_category_name VARCHAR(255) NOT NULL,
    bill_category_price DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (bill_id) REFERENCES bill(id) on DELETE set null
);