alter TABLE asset
drop CONSTRAINT asset_m_asset_id_fkey CASCADE;


ALTER TABLE asset add
FOREIGN KEY (m_asset_id) REFERENCES asset(id) ON
DELETE
set null;


alter TABLE asset
drop CONSTRAINT asset_facility_id_fkey CASCADE;


ALTER TABLE asset add
FOREIGN KEY (facility_id) REFERENCES facility(id) ON
DELETE
set null;