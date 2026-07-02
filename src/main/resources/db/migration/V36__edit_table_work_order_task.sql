alter TABLE if exists work_order
alter column work_order_start_date drop not null;

alter TABLE if exists task
alter column task_start_date drop not null;