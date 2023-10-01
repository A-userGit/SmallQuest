  package com.quest.dao.entities;

  import com.quest.commons.models.BaseRequirementModel;

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
