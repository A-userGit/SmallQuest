package com.quest.services.logic.functions;

import com.quest.commons.models.subaction.subactdata.SimpleIntActionData;
import com.quest.services.interfaces.ActionFunction;
import com.quest.services.logic.functions.argtypes.Answer;

import java.util.ArrayList;
import java.util.List;

public class SimpleFunction implements ActionFunction<Float,SimpleIntActionData,NumFuncAnswer> {

    @Override
    public List<NumFuncAnswer> performChange(Float itemData, SimpleIntActionData actionData) {
        NumFuncAnswer answer = new NumFuncAnswer();
        switch (actionData.getAction())
        {
            case DIV: answer.setValue(itemData / actionData.getChangeValue());
            break;
            case MUL: answer.setValue(itemData * actionData.getChangeValue());
            break;
            case PLUS: answer.setValue(itemData + actionData.getChangeValue());
            break;
            case MINUS: answer.setValue(itemData - actionData.getChangeValue());
            break;
            case PERCENT: answer.setValue(itemData* (actionData.getChangeValue()/100));
            break;
        }
        answer.setFunctionAnswerField(Answer.SINGLE_RESULT);
        List<NumFuncAnswer> answers = new ArrayList<>();
        answers.add(answer);
        return answers;
    }
}
