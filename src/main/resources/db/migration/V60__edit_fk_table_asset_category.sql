alter TABLE if exists asset_category
    add COLUMN m_asset_category_id UUID,
    add foreign key (m_asset_category_id) references asset_category (id);