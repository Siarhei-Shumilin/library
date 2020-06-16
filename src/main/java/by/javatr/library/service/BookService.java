package by.javatr.library.service;

import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class BookService {

    private DaoFactory daoFactory;

    public BookService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public List<Book> findAll() throws ServiceException {
        List<Book> books = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            books = bookDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return books;
    }

    public void updateBook(Book book) throws ServiceException {
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            bookDao.updateBook(book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }
    public Optional<Book> findById(int id) throws ServiceException {
        Optional<Book> book = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            book = bookDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return book;
    }
    public Optional<Book> findByTitle(String title) throws ServiceException {
        Optional<Book> book = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            book = bookDao.findByTitle(title);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return book;
    }

    public List<Book> findByGenre(String genre) throws ServiceException {
        List<Book> booksByGenre = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            booksByGenre = bookDao.findByGenre(genre);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return booksByGenre;
    }

    public void save(Book book) throws ServiceException {
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            bookDao.save(book);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void delete(Book book) throws ServiceException {
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            bookDao.removeByTitle(book.getTitle());
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Book> findPage(int number, int pageSize) throws ServiceException {
        List<Book> bookList = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            bookList = bookDao.findPage(number, pageSize);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(), e);
        }

        return bookList;
    }

    public long size() throws ServiceException {
        long size = 0;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            List<Book> all = bookDao.findAll();
            size = all.size();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return size;
    }

    public Set<String> getGenres() throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        Set<String> listGenres = new HashSet<>();
        try {
            List<Book> allBooks = bookDao.findAll();
            for (Book book : allBooks) {
                listGenres.add(book.getGenre());
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return listGenres;
    }
}
