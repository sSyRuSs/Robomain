alter TABLE account
    ADD COLUMN IF NOT EXISTS enterprise_id uuid REFERENCES enterprise(id) null;