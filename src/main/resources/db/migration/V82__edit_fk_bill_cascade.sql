-- Đổi ràng buộc khóa ngoại bill_id ở bill_category thành ON DELETE CASCADE
ALTER TABLE bill_category DROP CONSTRAINT IF EXISTS bill_category_bill_id_fkey;
ALTER TABLE bill_category ADD CONSTRAINT bill_category_bill_id_fkey FOREIGN KEY (bill_id) REFERENCES bill(id) ON DELETE CASCADE;

-- Đổi ràng buộc khóa ngoại bill_id ở surcharge thành ON DELETE CASCADE
ALTER TABLE surcharge DROP CONSTRAINT IF EXISTS surcharge_bill_id_fkey;
ALTER TABLE surcharge ADD CONSTRAINT surcharge_bill_id_fkey FOREIGN KEY (bill_id) REFERENCES bill(id) ON DELETE CASCADE;
