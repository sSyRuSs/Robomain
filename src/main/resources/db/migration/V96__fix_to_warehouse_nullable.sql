-- Fix to_warehouse_id to be nullable
-- Not all stock movements require a destination warehouse (e.g., OUT movements for work orders)

ALTER TABLE stock_movement 
ALTER COLUMN to_warehouse_id DROP NOT NULL;

-- Update comment
COMMENT ON COLUMN stock_movement.to_warehouse_id IS 'Destination warehouse (optional - only for IN/TRANSFER movements)';
