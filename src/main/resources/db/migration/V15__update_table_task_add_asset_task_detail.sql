alter TABLE task
add COLUMN asset_id UUID,
add COLUMN task_detail_id UUID,
add FOREIGN KEY (asset_id) REFERENCES asset (id) ON DELETE SET NULL,
add FOREIGN KEY (task_detail_id) REFERENCES task_detail (id) ON DELETE SET NULL;