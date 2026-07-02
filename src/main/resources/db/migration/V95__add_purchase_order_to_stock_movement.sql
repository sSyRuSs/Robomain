-- Add purchase_order_id column to stock_movement table
-- This links stock movements to purchase requests when receiving goods

ALTER TABLE stock_movement 
ADD COLUMN IF NOT EXISTS purchase_order_id UUID;

-- Add foreign key constraint
ALTER TABLE stock_movement
ADD CONSTRAINT fk_stock_movement_purchase_order 
FOREIGN KEY (purchase_order_id) 
REFERENCES purchase_request(id) 
ON DELETE SET NULL;

-- Add index for performance
CREATE INDEX IF NOT EXISTS idx_stock_movement_purchase_order_id ON stock_movement(purchase_order_id);

-- Update comment
COMMENT ON COLUMN stock_movement.purchase_order_id IS 'Reference to purchase request when receiving goods (IN movements)';
