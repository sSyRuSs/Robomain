-- Add soft delete columns to inventory table
-- This prevents data loss when inventory is deleted
-- All stock movements are preserved for audit trail

ALTER TABLE inventory 
ADD COLUMN deleted_at TIMESTAMP NULL,
ADD COLUMN deleted_by UUID NULL;

-- Add foreign key to account table for deleted_by
ALTER TABLE inventory 
ADD CONSTRAINT fk_inventory_deleted_by 
FOREIGN KEY (deleted_by) REFERENCES account(id);

-- Create index for soft delete queries (performance optimization)
CREATE INDEX idx_inventory_deleted_at ON inventory(deleted_at);
