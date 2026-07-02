-- Create ENUM types for purchase request
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'purchase_request_status_enum') THEN
        CREATE TYPE purchase_request_status_enum AS ENUM ('PENDING', 'APPROVED', 'REJECTED', 'ORDERED', 'RECEIVED');
    END IF;
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'purchase_request_type_enum') THEN
        CREATE TYPE purchase_request_type_enum AS ENUM ('AUTO', 'MANUAL');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS purchase_request (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    request_code VARCHAR(50) NOT NULL,
    inventory_id UUID NOT NULL,
    requested_quantity INTEGER NOT NULL,
    estimated_cost NUMERIC(19, 2) NOT NULL,
    req_status purchase_request_status_enum DEFAULT 'PENDING',
    request_type purchase_request_type_enum DEFAULT 'MANUAL',
    reason VARCHAR(500),
    requested_date DATE NOT NULL,
    required_date DATE,
    requested_by_id UUID NOT NULL,
    approved_by_id UUID,
    approved_date DATE,
    approval_notes VARCHAR(500),
    vendor_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_purchase_request_enterprise FOREIGN KEY (enterprise_id) 
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT fk_purchase_request_inventory FOREIGN KEY (inventory_id) 
        REFERENCES inventory(id) ON DELETE CASCADE,
    CONSTRAINT fk_purchase_request_requested_by FOREIGN KEY (requested_by_id) 
        REFERENCES account(id) ON DELETE RESTRICT,
    CONSTRAINT fk_purchase_request_approved_by FOREIGN KEY (approved_by_id) 
        REFERENCES account(id) ON DELETE SET NULL,
    CONSTRAINT fk_purchase_request_vendor FOREIGN KEY (vendor_id) 
        REFERENCES vendor(id) ON DELETE SET NULL,
    CONSTRAINT uq_request_code UNIQUE (request_code)
);

CREATE INDEX IF NOT EXISTS idx_purchase_request_enterprise_id ON purchase_request(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_purchase_request_inventory_id ON purchase_request(inventory_id);
CREATE INDEX IF NOT EXISTS idx_purchase_request_status ON purchase_request(req_status);
CREATE INDEX IF NOT EXISTS idx_purchase_request_type ON purchase_request(request_type);
CREATE INDEX IF NOT EXISTS idx_purchase_request_requested_by_id ON purchase_request(requested_by_id);
CREATE INDEX IF NOT EXISTS idx_purchase_request_approved_by_id ON purchase_request(approved_by_id);
CREATE INDEX IF NOT EXISTS idx_purchase_request_vendor_id ON purchase_request(vendor_id);
CREATE INDEX IF NOT EXISTS idx_purchase_request_requested_date ON purchase_request(requested_date);
CREATE INDEX IF NOT EXISTS idx_purchase_request_code ON purchase_request(request_code);
