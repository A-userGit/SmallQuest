package com.quest.commons.interfaces;

import java.io.IOException;

public interface FunctionWException<T,R> {
    public R apply(T source) throws Exception;
}
