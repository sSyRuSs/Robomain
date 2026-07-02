-- Fix backup_type and status columns from custom enum to VARCHAR
-- This makes them compatible with JPA @Enumerated(EnumType.STRING)

-- Step 1: Drop default values first (they depend on enum types)
ALTER TABLE database_backup 
ALTER COLUMN backup_type DROP DEFAULT;

ALTER TABLE database_backup 
ALTER COLUMN status DROP DEFAULT;

-- Step 2: Convert columns from enum to VARCHAR
ALTER TABLE database_backup 
ALTER COLUMN backup_type TYPE VARCHAR(50) USING backup_type::text;

ALTER TABLE database_backup 
ALTER COLUMN status TYPE VARCHAR(50) USING status::text;

-- Step 3: Now safe to drop enum types
DROP TYPE IF EXISTS backup_type_enum;
DROP TYPE IF EXISTS backup_status_enum;

-- Step 4: Re-add default values as VARCHAR
ALTER TABLE database_backup 
ALTER COLUMN backup_type SET DEFAULT 'MANUAL';

ALTER TABLE database_backup 
ALTER COLUMN status SET DEFAULT 'IN_PROGRESS';
