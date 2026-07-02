CREATE TABLE IF NOT EXISTS department(
    id UUID primary key,
    department_name varchar(255) not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null
);

