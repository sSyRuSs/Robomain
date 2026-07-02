CREATE TABLE IF NOT EXISTS chat_conversation (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    type VARCHAR(20) NOT NULL DEFAULT 'DIRECT',
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS chat_participant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id UUID NOT NULL REFERENCES chat_conversation(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES account(id) ON DELETE CASCADE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    CONSTRAINT uk_chat_participant UNIQUE (conversation_id, user_id)
);

CREATE TABLE IF NOT EXISTS chat_message (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    conversation_id UUID NOT NULL REFERENCES chat_conversation(id) ON DELETE CASCADE,
    sender_id UUID NOT NULL REFERENCES account(id) ON DELETE CASCADE,
    content TEXT NOT NULL,
    message_type VARCHAR(20) NOT NULL DEFAULT 'TEXT',
    client_message_id VARCHAR(80),
    is_deleted BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

CREATE TABLE IF NOT EXISTS chat_message_read (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    message_id UUID NOT NULL REFERENCES chat_message(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES account(id) ON DELETE CASCADE,
    read_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uk_chat_message_read UNIQUE (message_id, user_id)
);

CREATE UNIQUE INDEX IF NOT EXISTS uk_chat_message_client_sender
    ON chat_message(sender_id, client_message_id)
    WHERE client_message_id IS NOT NULL;

CREATE INDEX IF NOT EXISTS idx_chat_participant_user
    ON chat_participant(user_id);

CREATE INDEX IF NOT EXISTS idx_chat_participant_conversation
    ON chat_participant(conversation_id);

CREATE INDEX IF NOT EXISTS idx_chat_message_conversation_created
    ON chat_message(conversation_id, created_at DESC);

CREATE INDEX IF NOT EXISTS idx_chat_message_read_user
    ON chat_message_read(user_id);
