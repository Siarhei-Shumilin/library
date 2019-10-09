package by.javatr.library.command.impl.order;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class GiveOrderedBookCommand implements Command {
    private OrderService orderService;

    public GiveOrderedBookCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException, DaoException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.updateOrderById(id);
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()){
            BookDaoImpl bookDao = new BookDaoImpl(connection);
            Optional<Book> bookOptional = bookDao.findById(bookId);
            if (bookOptional.isPresent()){
                Book book = bookOptional.get();
                bookDao.saveBookInIssued(book);
            }
        }

        return new CommandResult(Constants.MAIN_COMMAND, false);
    }
}
