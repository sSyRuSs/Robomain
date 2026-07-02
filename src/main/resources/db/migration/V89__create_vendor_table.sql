DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'vendor_status_enum') THEN
        CREATE TYPE vendor_status_enum AS ENUM ('ACTIVE', 'INACTIVE', 'BLACKLISTED');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS vendor (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    vendor_code VARCHAR(50) NOT NULL,
    vendor_name VARCHAR(255) NOT NULL,
    vendor_description TEXT,
    contact_person VARCHAR(255),
    phone_number VARCHAR(20),
    email VARCHAR(255),
    vendor_address VARCHAR(500),
    city VARCHAR(100),
    country VARCHAR(100),
    payment_terms VARCHAR(100),
    tax_id VARCHAR(50),
    status vendor_status_enum DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_vendor_enterprise FOREIGN KEY (enterprise_id) 
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT uq_vendor_code_enterprise UNIQUE (enterprise_id, vendor_code)
);

CREATE INDEX IF NOT EXISTS idx_vendor_enterprise_id ON vendor(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_vendor_code ON vendor(vendor_code);
CREATE INDEX IF NOT EXISTS idx_vendor_status ON vendor(status);
