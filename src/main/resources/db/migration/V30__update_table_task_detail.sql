alter TABLE if EXISTS task_detail
ADD COLUMN IF NOT EXISTS file_info JSONB;