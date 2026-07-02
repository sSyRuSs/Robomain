ALTER TABLE account
DROP CONSTRAINT account_facility_id_fkey;

DROP TABLE IF EXISTS facility CASCADE;