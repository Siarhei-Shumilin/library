package by.javatr.library.dao;

import by.javatr.library.exception.DaoException;

import java.util.Optional;

public interface UserDao<T,K> extends Dao<T,K> {
    public Optional<T> findUserByLoginAndPassword(K login, K password) throws DaoException;
}
