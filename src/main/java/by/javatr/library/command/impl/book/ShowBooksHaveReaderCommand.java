package by.javatr.library.command.impl.book;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowBooksHaveReaderCommand implements Command {

    private BookService bookService;
    private OrderService orderService;

    public ShowBooksHaveReaderCommand(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        List<Order> ordersActive = orderService.findOrdersByStatus(OrderStatus.ACTIVE);
        if (ordersActive.isEmpty()) {
            request.setAttribute("booksEmpty", true);
        } else {
            request.setAttribute("orders", ordersActive);
        }
        return new CommandResult(Constants.BOOK, false);
    }
}
