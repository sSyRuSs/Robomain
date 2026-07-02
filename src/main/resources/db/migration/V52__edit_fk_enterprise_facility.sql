alter TABLE facility
add column enterprise_id UUID,
add foreign key (enterprise_id) references enterprise(id) on DELETE set null;