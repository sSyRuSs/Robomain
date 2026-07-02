alter TABLE task
ALTER COLUMN task_status SET DEFAULT 'Requested';

alter TABLE work_order
ALTER COLUMN work_order_status SET DEFAULT 'Requested';
