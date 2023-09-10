package com.quest.services.interfaces;

public interface Executor<T,K extends Executable> {
    public K execute(T data, K command);

}
