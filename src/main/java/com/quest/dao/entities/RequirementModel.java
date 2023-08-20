  package com.quest.dao.entities;

  import com.quest.commons.models.ActionType;

  public class RequirementModel extends ItemModel {

    private ActionType actionType;
    private boolean blocker;

    public boolean isBlocker() {
        return blocker;
    }

    public void setBlocker(boolean blocker) {
        blocker = blocker;
    }

      public ActionType getActionType() {
          return actionType;
      }

      public void setActionType(ActionType actionType) {
          this.actionType = actionType;
      }

      public RequirementModel(int id, String description, int value, boolean blocker) {
        super(id, description, value);
        this.blocker = blocker;
    }
}
