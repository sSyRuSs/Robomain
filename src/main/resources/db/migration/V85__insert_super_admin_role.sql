-- Insert SUPER_ADMIN role into role table
INSERT INTO role (id, role_name, created_at)
VALUES (gen_random_uuid(), 'SUPER_ADMIN', CURRENT_TIMESTAMP)
ON CONFLICT DO NOTHING;