-- Create video_call_room table
CREATE TABLE IF NOT EXISTS video_call_room (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    room_name VARCHAR(255) NOT NULL,
    room_code VARCHAR(50) UNIQUE NOT NULL,
    host_id UUID NOT NULL,
    status VARCHAR(50) DEFAULT 'WAITING',
    max_participants INTEGER DEFAULT 10,
    started_at TIMESTAMP,
    ended_at TIMESTAMP,
    is_recording BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_video_call_room_host FOREIGN KEY (host_id) REFERENCES account(id) ON DELETE CASCADE
);

-- Create call_participant table
CREATE TABLE IF NOT EXISTS call_participant (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    room_id UUID NOT NULL,
    user_id UUID NOT NULL,
    status VARCHAR(50) DEFAULT 'CONNECTING',
    joined_at TIMESTAMP,
    left_at TIMESTAMP,
    is_audio_enabled BOOLEAN DEFAULT TRUE,
    is_video_enabled BOOLEAN DEFAULT TRUE,
    is_screen_sharing BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_call_participant_room FOREIGN KEY (room_id) REFERENCES video_call_room(id) ON DELETE CASCADE,
    CONSTRAINT fk_call_participant_user FOREIGN KEY (user_id) REFERENCES account(id) ON DELETE CASCADE
);

-- Create indexes for better query performance
CREATE INDEX idx_video_call_room_code ON video_call_room(room_code);
CREATE INDEX idx_video_call_room_host ON video_call_room(host_id);
CREATE INDEX idx_video_call_room_status ON video_call_room(status);
CREATE INDEX idx_call_participant_room ON call_participant(room_id);
CREATE INDEX idx_call_participant_user ON call_participant(user_id);
CREATE INDEX idx_call_participant_status ON call_participant(status);
