-- Create ENUM type for inventory status
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'inventory_status_enum') THEN
        CREATE TYPE inventory_status_enum AS ENUM ('ACTIVE', 'INACTIVE', 'DISCONTINUED');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS inventory (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    item_code VARCHAR(50) NOT NULL,
    item_name VARCHAR(255) NOT NULL,
    description TEXT,
    quantity INTEGER DEFAULT 0,
    unit_cost NUMERIC(19, 2) NOT NULL,
    min_stock_level INTEGER DEFAULT 5,
    reorder_point INTEGER DEFAULT 10,
    reorder_quantity INTEGER DEFAULT 20,
    category_id UUID,
    warehouse_id UUID NOT NULL,
    preferred_supplier_id UUID,
    unit_of_measure VARCHAR(50) DEFAULT 'PIECE',
    location VARCHAR(255),
    status inventory_status_enum DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_inventory_enterprise FOREIGN KEY (enterprise_id) 
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT fk_inventory_category FOREIGN KEY (category_id) 
        REFERENCES inventory_category(id) ON DELETE SET NULL,
    CONSTRAINT fk_inventory_warehouse FOREIGN KEY (warehouse_id) 
        REFERENCES warehouse(id) ON DELETE RESTRICT,
    CONSTRAINT fk_inventory_supplier FOREIGN KEY (preferred_supplier_id) 
        REFERENCES vendor(id) ON DELETE SET NULL,
    CONSTRAINT uq_item_code_enterprise UNIQUE (enterprise_id, item_code)
);

CREATE INDEX IF NOT EXISTS idx_inventory_enterprise_id ON inventory(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_inventory_category_id ON inventory(category_id);
CREATE INDEX IF NOT EXISTS idx_inventory_warehouse_id ON inventory(warehouse_id);
CREATE INDEX IF NOT EXISTS idx_inventory_supplier_id ON inventory(preferred_supplier_id);
CREATE INDEX IF NOT EXISTS idx_inventory_item_code ON inventory(item_code);
CREATE INDEX IF NOT EXISTS idx_inventory_low_stock ON inventory(quantity, min_stock_level);
CREATE INDEX IF NOT EXISTS idx_inventory_status ON inventory(status);
