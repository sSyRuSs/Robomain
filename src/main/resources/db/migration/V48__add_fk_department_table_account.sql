ALTER TABLE account
DROP COLUMN facility_id CASCADE;

ALTER TABLE account
ADD COLUMN department_id UUID;

ALTER TABLE account
ADD CONSTRAINT account_department_id_fkey FOREIGN KEY (department_id) REFERENCES department(id) ON DELETE SET NULL;