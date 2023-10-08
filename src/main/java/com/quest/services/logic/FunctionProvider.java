package com.quest.services.logic;

import com.quest.commons.types.ActionFunctionType;
import com.quest.services.interfaces.ActionFunction;
import com.quest.services.logic.functions.RandomFunction;
import com.quest.services.logic.functions.SimpleFunction;

public class FunctionProvider {
    public static ActionFunction getFunction(ActionFunctionType functionType)
    {
        switch (functionType)
        {
            case RAND: return new RandomFunction();
            case SIMPL_ARITHM: return new SimpleFunction();
        }
        return null;
    }
}
