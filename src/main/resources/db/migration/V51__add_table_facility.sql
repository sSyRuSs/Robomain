CREATE TYPE EnumType AS ENUM (
    'Headquarter',
    'Branch',
    'Representative office',
    'Transaction office',
    'Permanent representative office',
    'Factory',
    'Plant',
    'Warehouse',
    'Storehouse',
    'Depot',
    'Garage',
    'Cold storage',
    'Office building'
);

create TABLE IF NOT EXISTS facility(
    id UUID primary key,
    facility_name varchar(255) not null,
    facility_code varchar(255),
    facility_email varchar(255) not null,
    facility_phone varchar(255) not null,
    facility_address varchar(255) not null,
    facility_type EnumType not null,
    m_facility_id UUID,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp,
    foreign key (m_facility_id) references facility(id) on DELETE set null
);