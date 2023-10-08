package com.quest.services.logic.functions;

import com.quest.commons.models.subactdata.RandomActionData;
import com.quest.services.interfaces.ActionFunction;
import com.quest.services.logic.functions.argtypes.Answer;

import java.util.ArrayList;
import java.util.List;

public class RandomFunction implements ActionFunction<Integer, RandomActionData, NumFuncAnswer> {
    @Override
    public List<NumFuncAnswer> performChange(Integer itemData, RandomActionData actionData) {
        double rand = Math.random() *(actionData.getTopBorder() - actionData.getFloorBorder() + 1) + actionData.getFloorBorder();
        NumFuncAnswer answer =  new NumFuncAnswer();
        answer.setFunctionAnswerField(Answer.SINGLE_RESULT);
        answer.setValue((float) rand);
        List<NumFuncAnswer> answers = new ArrayList<>();
        answers.add(answer);
        return answers;
    }
}
