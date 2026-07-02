alter TABLE task
add FOREIGN KEY (work_order_id) REFERENCES work_order (id) ON DELETE SET NULL;