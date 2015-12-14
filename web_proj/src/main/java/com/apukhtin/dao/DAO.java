package com.apukhtin.dao;

public interface DAO<T> {
    T add(T obj) throws DAOException;

    boolean remove(T obj) throws DAOException;

    T get(long id) throws DAOException;

    void update(T obj) throws DAOException;

    boolean contains(long id) throws DAOException;
}
