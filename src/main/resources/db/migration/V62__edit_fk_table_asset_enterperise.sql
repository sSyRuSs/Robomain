alter TABLE if exists asset
    add COLUMN enterprise_id UUID,
    add foreign key (enterprise_id) references enterprise (id);