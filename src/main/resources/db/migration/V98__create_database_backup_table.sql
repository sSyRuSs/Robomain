-- Create database_backup table for backup management
-- Only accessible by SUPER_ADMIN role
-- Tracks backup creation, restoration, and storage

-- Create backup type enum
CREATE TYPE backup_type_enum AS ENUM ('MANUAL', 'SCHEDULED');

-- Create backup status enum
CREATE TYPE backup_status_enum AS ENUM ('IN_PROGRESS', 'SUCCESS', 'FAILED');

-- Create database_backup table
CREATE TABLE database_backup (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    backup_name VARCHAR(255) NOT NULL UNIQUE,
    file_path VARCHAR(500) NOT NULL,
    file_size_bytes BIGINT,
    backup_type backup_type_enum NOT NULL DEFAULT 'MANUAL',
    status backup_status_enum NOT NULL DEFAULT 'IN_PROGRESS',
    error_message TEXT,
    started_at TIMESTAMP NOT NULL,
    completed_at TIMESTAMP,
    created_by_user_id UUID NOT NULL,
    description TEXT,
    database_name VARCHAR(255),
    tables_count INTEGER,
    records_count BIGINT,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_database_backup_created_by 
        FOREIGN KEY (created_by_user_id) 
        REFERENCES account(id) 
        ON DELETE CASCADE
);

-- Create indexes for performance
CREATE INDEX idx_database_backup_status ON database_backup(status);
CREATE INDEX idx_database_backup_created_at ON database_backup(created_at DESC);
CREATE INDEX idx_database_backup_backup_type ON database_backup(backup_type);
CREATE INDEX idx_database_backup_created_by ON database_backup(created_by_user_id);

-- Add comments for documentation
COMMENT ON TABLE database_backup IS 'Database backup tracking and management (SUPER_ADMIN only)';
COMMENT ON COLUMN database_backup.backup_name IS 'Unique name for the backup (e.g., backup_20241215_143022)';
COMMENT ON COLUMN database_backup.file_path IS 'Full file system path to the backup file';
COMMENT ON COLUMN database_backup.file_size_bytes IS 'Size of the backup file in bytes';
COMMENT ON COLUMN database_backup.backup_type IS 'MANUAL or SCHEDULED backup';
COMMENT ON COLUMN database_backup.status IS 'IN_PROGRESS, SUCCESS, or FAILED';
COMMENT ON COLUMN database_backup.error_message IS 'Error details if backup failed';
COMMENT ON COLUMN database_backup.started_at IS 'Timestamp when backup started';
COMMENT ON COLUMN database_backup.completed_at IS 'Timestamp when backup completed (success or failure)';
COMMENT ON COLUMN database_backup.created_by_user_id IS 'SUPER_ADMIN user who created the backup';
COMMENT ON COLUMN database_backup.description IS 'User-provided description of the backup';
COMMENT ON COLUMN database_backup.database_name IS 'Name of the database that was backed up';
COMMENT ON COLUMN database_backup.tables_count IS 'Number of tables in the backup';
COMMENT ON COLUMN database_backup.records_count IS 'Total number of records in the backup';
