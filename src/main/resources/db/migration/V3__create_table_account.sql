CREATE TABLE IF NOT EXISTS account (
    id UUID PRIMARY KEY, 
    username VARCHAR(255) NOT NULL, 
    password VARCHAR(255), 
    email VARCHAR(255), 
    phone VARCHAR(15),
    address VARCHAR(255),
    facility_id UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, 
    updated_at TIMESTAMP,
    FOREIGN KEY (facility_id) REFERENCES facility (id) ON DELETE SET NULL
);
