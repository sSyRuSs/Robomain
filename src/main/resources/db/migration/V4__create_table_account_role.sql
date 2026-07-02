CREATE TABLE IF NOT EXISTS account_role(
    user_id UUID,
    role_id UUID,
    PRIMARY KEY (user_id, role_id), 
    FOREIGN KEY (user_id) REFERENCES account (id) ON DELETE SET NULL, 
    FOREIGN KEY (role_id) REFERENCES role (id) ON DELETE SET NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP
);