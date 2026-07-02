ALTER TABLE chat_conversation
    ADD COLUMN IF NOT EXISTS can_send_messages BOOLEAN NOT NULL DEFAULT TRUE;

ALTER TABLE chat_message
    ADD COLUMN IF NOT EXISTS is_pinned BOOLEAN NOT NULL DEFAULT FALSE;

CREATE INDEX IF NOT EXISTS idx_chat_message_conversation_pinned
    ON chat_message(conversation_id, is_pinned, created_at DESC);
