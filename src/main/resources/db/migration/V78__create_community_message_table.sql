CREATE TABLE IF NOT EXISTS community_message (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    content TEXT,
    room_id VARCHAR(255),
    message_type VARCHAR(20),
    is_edited BOOLEAN DEFAULT FALSE,
    parent_message_id UUID,
    reactions JSONB DEFAULT '{}'::jsonb,
    read_by JSONB DEFAULT '[]'::jsonb,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    sender_id UUID NOT NULL,
    FOREIGN KEY (sender_id) REFERENCES account(id) ON DELETE CASCADE,
    FOREIGN KEY (parent_message_id) REFERENCES community_message(id) ON DELETE SET NULL
);

-- Create indexes for better query performance
CREATE INDEX IF NOT EXISTS idx_community_message_room_id ON community_message(room_id);
CREATE INDEX IF NOT EXISTS idx_community_message_sender_id ON community_message(sender_id);
CREATE INDEX IF NOT EXISTS idx_community_message_parent_id ON community_message(parent_message_id);
CREATE INDEX IF NOT EXISTS idx_community_message_created_at ON community_message(created_at); 