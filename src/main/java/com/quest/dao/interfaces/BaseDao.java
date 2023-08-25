package com.quest.dao.interfaces;

import java.util.List;

public interface BaseDao<T> {
    public List<T> getList();
    public boolean saveEntity(T entity);

    public boolean saveAll(List<T> list);
}
