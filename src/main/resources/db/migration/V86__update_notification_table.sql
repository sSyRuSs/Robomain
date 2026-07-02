-- Add more fields to notification table for better tracking
ALTER TABLE notification ADD COLUMN IF NOT EXISTS reference_id UUID;
ALTER TABLE notification ADD COLUMN IF NOT EXISTS reference_type VARCHAR(50);
ALTER TABLE notification ADD COLUMN IF NOT EXISTS link VARCHAR(500);
ALTER TABLE notification ADD COLUMN IF NOT EXISTS priority VARCHAR(20) DEFAULT 'MEDIUM';
ALTER TABLE notification ADD COLUMN IF NOT EXISTS actor_id UUID;

-- Add foreign key for actor
ALTER TABLE notification ADD CONSTRAINT fk_notification_actor 
    FOREIGN KEY (actor_id) REFERENCES account(id) ON DELETE SET NULL;

-- Add index for better query performance
CREATE INDEX IF NOT EXISTS idx_notification_user_isread ON notification(user_id, is_read);
CREATE INDEX IF NOT EXISTS idx_notification_reference ON notification(reference_type, reference_id);
CREATE INDEX IF NOT EXISTS idx_notification_created_at ON notification(created_at DESC);
