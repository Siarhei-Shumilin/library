package by.javatr.library.dao;

import by.javatr.library.exception.DaoException;

import java.util.List;

public interface Dao<T, K> {
    List<T> findAll() throws DaoException;

}
