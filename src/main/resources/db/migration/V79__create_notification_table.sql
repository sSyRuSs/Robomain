CREATE TABLE IF NOT EXISTS notification (
    id UUID PRIMARY KEY,
    user_id UUID NOT NULL,
    title VARCHAR(255) NOT NULL,
    message TEXT NOT NULL,
    type VARCHAR(50) NOT NULL,
    is_read BOOLEAN DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account(id) ON DELETE CASCADE
);

CREATE INDEX idx_notification_user_id ON notification(user_id);
CREATE INDEX idx_notification_created_at ON notification(created_at);
CREATE INDEX idx_notification_is_read ON notification(is_read); 