package com.quest.commons.interfaces;

@FunctionalInterface
public interface BiConsumerWException<T,U> {
    public void accept(T arg1, U arg2) throws Exception;
}
