create table if not EXISTS business_classification(
    id UUID primary key,
    business_classification_name varchar(255) not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null
);
