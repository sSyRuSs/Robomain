alter TABLE if exists asset
    add COLUMN m_asset_id UUID,
    add foreign key (m_asset_id) references asset (id);

