package com.quest.dao.interfaces;

import com.quest.commons.models.ContainerItemModel;
import com.quest.commons.models.ItemModel;
import com.quest.commons.types.ItemDaoType;
import com.quest.dao.entities.ContainerIdElement;

import java.util.List;

public interface ItemsDao extends BaseDao<ItemModel> {

    boolean saveContainerEntity(ContainerItemModel<ContainerIdElement> itemModel);

    void setItemDaoType(ItemDaoType itemDaoType);
}
