package com.quest.dao.interfaces;

import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;

import java.util.List;

public interface ItemsDao extends BaseDao<ItemModel> {

    void setItemDaoType(ItemDaoType itemDaoType);
}
