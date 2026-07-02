ALTER TABLE chat_conversation
    ADD COLUMN IF NOT EXISTS name VARCHAR(120),
    ADD COLUMN IF NOT EXISTS department_id UUID REFERENCES department(id),
    ADD COLUMN IF NOT EXISTS enterprise_id UUID REFERENCES enterprise(id),
    ADD COLUMN IF NOT EXISTS created_by UUID REFERENCES account(id),
    ADD COLUMN IF NOT EXISTS is_deleted BOOLEAN NOT NULL DEFAULT FALSE;

ALTER TABLE chat_participant
    ADD COLUMN IF NOT EXISTS role VARCHAR(20) NOT NULL DEFAULT 'MEMBER';

CREATE INDEX IF NOT EXISTS idx_chat_conversation_room_access
    ON chat_conversation(type, enterprise_id, department_id, is_deleted);
