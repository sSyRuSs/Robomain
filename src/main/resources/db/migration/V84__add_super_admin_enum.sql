-- Add new role to EnumRole type
DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'enumrole') THEN
        CREATE TYPE EnumRole AS ENUM ('ADMIN', 'MANAGER', 'LEADER', 'TECHNICIAN', 'SUPER_ADMIN');
    ELSE
        -- Add value to existing enum type
        IF NOT EXISTS (SELECT 1 FROM pg_enum WHERE enumlabel = 'SUPER_ADMIN' AND enumtypid = (SELECT oid FROM pg_type WHERE typname = 'enumrole')) THEN
            ALTER TYPE EnumRole ADD VALUE 'SUPER_ADMIN';
        END IF;
    END IF;
END$$;
