package by.javatr.library.command.impl.order;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelOrderCommand extends AbstractCommand {

    public CancelOrderCommand(BookService bookService, OrderService orderService) {
        super(bookService, orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.updateOrderById(id, OrderStatus.CLOSE);
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        returnBook(bookId);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (Role.LIBRARIAN.equals(user.getRole())){
            return new CommandResult(Constants.MAIN_COMMAND, true);
        } else {
            return new CommandResult(Constants.SHOW_USER_COMMAND, true);
        }
    }
}
