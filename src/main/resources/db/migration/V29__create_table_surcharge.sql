create TABLE if not exists surcharge  (
    id UUID primary key,
    surcharge_name varchar(255) not null,
    surcharge_quantity int not null,
    surcharge_unit_price DECIMAL(14,2) not null,
    surcharge_unit VARCHAR not null,
    surcharge_total DECIMAL(14,2) not null,
    bill_id UUID not null,
    created_at timestamp not null default CURRENT_TIMESTAMP,
    updated_at timestamp not null,
    foreign key (bill_id) references bill(id)
);