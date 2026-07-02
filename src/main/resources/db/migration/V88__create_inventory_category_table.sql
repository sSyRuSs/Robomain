CREATE TABLE IF NOT EXISTS inventory_category (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    category_code VARCHAR(50) NOT NULL,
    category_name VARCHAR(255) NOT NULL,
    category_description TEXT,
    parent_category_id UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_inventory_category_enterprise FOREIGN KEY (enterprise_id) 
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT fk_inventory_category_parent FOREIGN KEY (parent_category_id) 
        REFERENCES inventory_category(id) ON DELETE SET NULL,
    CONSTRAINT uq_category_code_enterprise UNIQUE (enterprise_id, category_code)
);

CREATE INDEX IF NOT EXISTS idx_inventory_category_enterprise_id ON inventory_category(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_inventory_category_parent_id ON inventory_category(parent_category_id);
CREATE INDEX IF NOT EXISTS idx_inventory_category_code ON inventory_category(category_code);
