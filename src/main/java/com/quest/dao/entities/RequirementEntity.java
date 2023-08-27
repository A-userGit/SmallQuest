  package com.quest.dao.entities;

  import com.quest.commons.models.BaseModel;
  import com.quest.commons.models.BaseRequirementModel;
  import com.quest.commons.types.ItemActionType;
  import com.quest.commons.models.ItemModel;
  import com.quest.commons.types.ItemType;
  import com.quest.commons.types.RestrictionType;

  public class RequirementEntity extends BaseRequirementModel {

    private int itemId;

    public RequirementEntity(int id, String description, int value, boolean blocker) {
      super(id, description, value, blocker);
    }

    public int getItemId() {
      return itemId;
    }

    public void setItemId(int itemId) {
      this.itemId = itemId;
    }
  }
