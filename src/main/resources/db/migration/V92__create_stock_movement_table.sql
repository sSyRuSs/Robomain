-- Create ENUM type for movement type
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'stock_movement_type_enum') THEN
        CREATE TYPE stock_movement_type_enum AS ENUM ('IN', 'OUT', 'TRANSFER', 'ADJUSTMENT');
    END IF;
END
$$;

CREATE TABLE IF NOT EXISTS stock_movement (
    id UUID PRIMARY KEY,
    enterprise_id UUID NOT NULL,
    movement_code VARCHAR(50) NOT NULL,
    movement_type stock_movement_type_enum NOT NULL,
    inventory_id UUID NOT NULL,
    quantity INTEGER NOT NULL,
    unit_cost NUMERIC(19, 2) NOT NULL,
    total_cost NUMERIC(19, 2),
    from_warehouse_id UUID,
    to_warehouse_id UUID NOT NULL,
    work_order_id UUID,
    task_id UUID,
    reason VARCHAR(500),
    reference VARCHAR(100),
    processed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    processed_by UUID,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_stock_movement_enterprise FOREIGN KEY (enterprise_id) 
        REFERENCES enterprise(id) ON DELETE CASCADE,
    CONSTRAINT fk_stock_movement_inventory FOREIGN KEY (inventory_id) 
        REFERENCES inventory(id) ON DELETE CASCADE,
    CONSTRAINT fk_stock_movement_from_warehouse FOREIGN KEY (from_warehouse_id) 
        REFERENCES warehouse(id) ON DELETE SET NULL,
    CONSTRAINT fk_stock_movement_to_warehouse FOREIGN KEY (to_warehouse_id) 
        REFERENCES warehouse(id) ON DELETE RESTRICT,
    CONSTRAINT fk_stock_movement_work_order FOREIGN KEY (work_order_id) 
        REFERENCES work_order(id) ON DELETE SET NULL,
    CONSTRAINT fk_stock_movement_task FOREIGN KEY (task_id) 
        REFERENCES task(id) ON DELETE SET NULL,
    CONSTRAINT fk_stock_movement_user FOREIGN KEY (processed_by) 
        REFERENCES account(id) ON DELETE SET NULL
);

-- Create trigger to calculate total_cost
CREATE OR REPLACE FUNCTION calculate_stock_movement_total()
RETURNS TRIGGER AS $$
BEGIN
    NEW.total_cost := NEW.quantity * NEW.unit_cost;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER stock_movement_total_trigger
BEFORE INSERT OR UPDATE ON stock_movement
FOR EACH ROW
EXECUTE FUNCTION calculate_stock_movement_total();

CREATE INDEX IF NOT EXISTS idx_stock_movement_enterprise_id ON stock_movement(enterprise_id);
CREATE INDEX IF NOT EXISTS idx_stock_movement_inventory_id ON stock_movement(inventory_id);
CREATE INDEX IF NOT EXISTS idx_stock_movement_type ON stock_movement(movement_type);
CREATE INDEX IF NOT EXISTS idx_stock_movement_from_warehouse_id ON stock_movement(from_warehouse_id);
CREATE INDEX IF NOT EXISTS idx_stock_movement_to_warehouse_id ON stock_movement(to_warehouse_id);
CREATE INDEX IF NOT EXISTS idx_stock_movement_work_order_id ON stock_movement(work_order_id);
CREATE INDEX IF NOT EXISTS idx_stock_movement_processed_at ON stock_movement(processed_at);
CREATE INDEX IF NOT EXISTS idx_stock_movement_code ON stock_movement(movement_code);
