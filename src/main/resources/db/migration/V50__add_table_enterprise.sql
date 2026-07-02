create TABLE IF NOT EXISTS enterprise(
    id UUID primary key,
    enterprise_name varchar(255) not null,
    enterprise_code varchar(255),
    enterprise_email varchar(255) not null,
    enterprise_phone varchar(255) not null,
    enterprise_address varchar(255) not null,
    business_classification_id UUID,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null,
    foreign key (business_classification_id) references business_classification(id) on delete set null
);