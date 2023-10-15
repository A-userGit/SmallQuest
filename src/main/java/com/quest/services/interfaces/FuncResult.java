package com.quest.services.interfaces;

import com.quest.commons.types.ActionFunctionType;
import com.quest.services.logic.functions.argtypes.Answer;

public interface FuncResult {
    public Answer getAnswerField();
    public ActionFunctionType getFunctionType();
}
