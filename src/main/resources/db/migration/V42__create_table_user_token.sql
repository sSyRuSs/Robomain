CREATE TABLE IF NOT EXISTS user_token (
    id UUID PRIMARY KEY, 
    user_id UUID,
    access_token VARCHAR(1000) NOT NULL, 
    refresh_token VARCHAR(1000) NOT NULL,
    refresh_token_expire TIMESTAMP NOT NULL, 
    is_reseted BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES account (id) ON DELETE CASCADE
);