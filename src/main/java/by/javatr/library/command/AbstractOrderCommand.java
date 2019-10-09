package by.javatr.library.command;

import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;

import java.util.Optional;

public abstract class AbstractOrderCommand implements Command {
    protected OrderService orderService;

    public AbstractOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    protected void returnBook(int bookId) throws ServiceException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            BookDaoImpl bookDao = new BookDaoImpl(connection);
            Optional<Book> bookOptional = bookDao.findById(bookId);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                int numberAvailableOfInstances = book.getNumberAvailableOfInstances();
                book.setNumberAvailableOfInstances(numberAvailableOfInstances + 1);
                bookDao.updateBook(book);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    protected void giveBook(int bookId) throws ServiceException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            BookDaoImpl bookDao = new BookDaoImpl(connection);
            Optional<Book> bookOptional = bookDao.findById(bookId);
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                int numberAvailableOfInstances = book.getNumberAvailableOfInstances();
                book.setNumberAvailableOfInstances(numberAvailableOfInstances - 1);
                bookDao.updateBook(book);
            }
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

}

