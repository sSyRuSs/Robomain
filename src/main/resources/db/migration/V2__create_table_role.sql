CREATE TYPE EnumRole AS ENUM (
      'ADMIN',
      'MANAGER',
      'LEADER',
      'TECHNICIAN'
);

CREATE TABLE IF NOT EXISTS role (
      id UUID PRIMARY KEY,
      role_name EnumRole NOT NULL,
      created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      updated_at TIMESTAMP
);