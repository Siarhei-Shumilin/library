package by.javatr.library.dao;

import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface BookDao<T,K> extends Dao<T,K>  {
    void updateBook(Book book) throws DaoException;
    Optional<Book> findByTitle(String title) throws DaoException;
    void removeByTitle(String title) throws DaoException;
    void save(T entity) throws DaoException;
    Optional<Book> findById(int id) throws DaoException;
    List<Book> findByGenre(String genre) throws DaoException;
    List<Book> findPage(int number, int pageSize) throws DaoException;
    long size() throws DaoException;
}
