  package com.quest.dao.entities;

  import com.quest.commons.types.ItemActionType;
  import com.quest.commons.models.ItemModel;
  import com.quest.commons.types.ItemType;
  import com.quest.commons.types.RestrictionType;

  public class RequirementEntity extends ItemModel {

    private ItemActionType actionType;
    private boolean blocker;

    private ItemType itemType;

    private RestrictionType restrictionType;

    public boolean isBlocker() {
        return blocker;
    }

    public void setBlocker(boolean blocker) {
        blocker = blocker;
    }

      public ItemActionType getActionType() {
          return actionType;
      }

      public void setActionType(ItemActionType actionType) {
          this.actionType = actionType;
      }

      public RequirementEntity(int id, String description, int value, boolean blocker) {
        super(id, description, value);
        this.blocker = blocker;
    }

    public ItemType getItemType() {
      return itemType;
    }

    public void setItemType(ItemType itemType) {
      this.itemType = itemType;
    }

    public RestrictionType getRestrictionType() {
      return restrictionType;
    }

    public void setRestrictionType(RestrictionType restrictionType) {
      this.restrictionType = restrictionType;
    }
  }
