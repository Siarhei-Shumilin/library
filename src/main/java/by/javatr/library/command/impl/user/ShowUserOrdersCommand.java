package by.javatr.library.command.impl.user;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowUserOrdersCommand extends AbstractCommand {


    public ShowUserOrdersCommand(OrderService orderService) {
        super(orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        int id = 0;
        User user = (User) session.getAttribute("user");
        List<Order> orderByUserId = null;
        if (Role.ADMIN.equals(user.getRole())) {
            id = Integer.parseInt(request.getParameter("id"));
            orderByUserId = orderService.findAllOrdersByUserId(id);
            request.setAttribute("admin", true);
        } else if (Role.READER.equals(user.getRole())) {
            id = user.getId();
            orderByUserId = orderService.findNewOrdersByUserId(id);
            request.setAttribute("reader", true);
        } else if (Role.LIBRARIAN.equals(user.getRole())){
            id = Integer.parseInt(request.getParameter("id"));
            orderByUserId = orderService.findNewOrdersByUserId(id);
        }

        if (orderByUserId.size() == 0) {
            request.setAttribute("bookEmpty", true);
        }
        request.setAttribute("orders", orderByUserId);
        return new CommandResult(Constants.ORDER, false);
    }
}
