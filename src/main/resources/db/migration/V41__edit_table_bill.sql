alter TABLE bill
DROP CONSTRAINT bill_task_id_fkey,
ADD FOREIGN KEY (task_id) REFERENCES task(id) ON DELETE SET NULL;