package by.javatr.library.command.impl.order;

import by.javatr.library.command.AbstractOrderCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class CancelOrderCommand extends AbstractOrderCommand {

    public CancelOrderCommand(OrderService orderService) {
        super(orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        orderService.updateOrderById(id);
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
