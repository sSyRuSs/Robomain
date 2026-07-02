-- Create ENUM type for warehouse_type
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'warehouse_type_enum') THEN
        CREATE TYPE warehouse_type_enum AS ENUM ('MAIN', 'SATELLITE', 'MOBILE');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS warehouse (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    facility_id UUID NOT NULL,
    warehouse_code VARCHAR(50) NOT NULL,
    warehouse_name VARCHAR(255) NOT NULL,
    warehouse_address VARCHAR(500),
    manager_name VARCHAR(255),
    contact_phone VARCHAR(20),
    contact_email VARCHAR(255),
    warehouse_type warehouse_type_enum DEFAULT 'MAIN',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_warehouse_enterprise FOREIGN KEY (enterprise_id) 
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT fk_warehouse_facility FOREIGN KEY (facility_id) 
        REFERENCES facility(id) ON DELETE CASCADE,
    CONSTRAINT uq_warehouse_code UNIQUE (warehouse_code)
);

CREATE INDEX IF NOT EXISTS idx_warehouse_enterprise_id ON warehouse(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_warehouse_facility_id ON warehouse(facility_id);
CREATE INDEX IF NOT EXISTS idx_warehouse_code ON warehouse(warehouse_code);
