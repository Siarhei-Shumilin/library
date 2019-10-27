package by.javatr.library.command.impl.order;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class GiveOrderedBookCommand extends AbstractCommand {

    public GiveOrderedBookCommand(BookService bookService, OrderService orderService) {
        super(bookService, orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.updateOrderById(id, OrderStatus.ACTIVE);
        return new CommandResult(Constants.MAIN_COMMAND, true);
    }
}
