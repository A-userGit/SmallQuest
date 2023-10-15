package com.quest.services.logic.functions;


import com.quest.services.logic.functions.argtypes.Answer;

public class NumFuncAnswer {
    private int actionId;

    private float value;

    private Answer functionAnswerField;

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public Answer getFunctionAnswerField() {
        return functionAnswerField;
    }

    public void setFunctionAnswerField(Answer functionAnswerField) {
        this.functionAnswerField = functionAnswerField;
    }
}
