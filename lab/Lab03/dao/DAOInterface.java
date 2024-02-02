package org.example.dao;

import java.util.List;

public interface DAOInterface<T> {
    boolean add(T t);

    T get(String id);

    List<T> getAll();

    boolean remove(String id);

    boolean remove(T t);

    boolean update(T t);
}
