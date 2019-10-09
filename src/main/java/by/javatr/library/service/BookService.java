package by.javatr.library.service;

import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Optional;

public class BookService {
    private final static Logger LOGGER = Logger.getLogger(BookService.class);

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
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return books;
    }

    public void updateBook(Book book) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        try {
            bookDao.updateBook(book);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }
    public Optional<Book> findById(int id) throws ServiceException {
        Optional<Book> book = null;
        try {
            BookDaoImpl bookDao = daoFactory.createBookDao();
            book = bookDao.findById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
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
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return book;
    }

    public List<Book> findByGenre(String genre) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        List<Book> booksByGenre = null;
        try {
            booksByGenre = bookDao.findByGenre(genre);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return booksByGenre;
    }

    public void save(Book book) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        try {
            bookDao.save(book);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void delete(Book book) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        try {
            bookDao.removeByTitle(book.getTitle());
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public void deleteBooksIssued(int bookId) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        try {
            bookDao.removeBooksIssued(bookId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Book> findPage(int number, int pageSize) throws ServiceException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        List<Book> bookList = null;
        try {
            bookList = bookDao.findPage(number, pageSize);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(), e);
        }

        return bookList;
    }

    public long size() throws DaoException {
        BookDaoImpl bookDao = daoFactory.createBookDao();
        List<Book> all = bookDao.findAll();
        return all.size();
    }
}
