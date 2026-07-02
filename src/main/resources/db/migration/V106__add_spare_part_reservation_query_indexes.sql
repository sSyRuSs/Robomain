CREATE INDEX IF NOT EXISTS idx_spare_reservation_enterprise_reserved_at
    ON spare_part_reservation(enterprise_id, reserved_at DESC);

CREATE INDEX IF NOT EXISTS idx_spare_reservation_work_order_reserved_at
    ON spare_part_reservation(work_order_id, reserved_at DESC);

CREATE INDEX IF NOT EXISTS idx_spare_reservation_inventory_status
    ON spare_part_reservation(inventory_id, status);
