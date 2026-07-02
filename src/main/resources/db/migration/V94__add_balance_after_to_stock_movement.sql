-- Add balance_after column to stock_movement table
-- This tracks the inventory quantity after each movement for audit trail

ALTER TABLE stock_movement 
ADD COLUMN IF NOT EXISTS balance_after INTEGER NOT NULL DEFAULT 0;

-- Add index for performance on audit queries
CREATE INDEX IF NOT EXISTS idx_stock_movement_balance_after ON stock_movement(balance_after);

-- Update comment
COMMENT ON COLUMN stock_movement.balance_after IS 'Inventory quantity after this movement (for audit trail)';
