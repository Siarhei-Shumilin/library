package by.javatr.library.command.impl.order;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.service.UserService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class SearchOrderCommand extends AbstractCommand {

    private UserService userService;

    public SearchOrderCommand(OrderService orderService, UserService userService) {
        super(orderService);
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        User user = null;
        String name = request.getParameter("name");
        Optional<User> userOptional = userService.findByName(name);
        if (userOptional.isPresent()) {
            user = userOptional.get();
        } else {
            request.setAttribute("bookEmpty", true);
            return new CommandResult(Constants.MAIN_COMMAND, false);
        }

        List<Order> orderByUserId = orderService.findNewOrdersByUserId(user.getId());
        if (orderByUserId.size() == 0) {
            request.setAttribute("bookEmpty", true);
        }
        request.setAttribute("orders", orderByUserId);
        return new CommandResult(Constants.LIBRARIAN, false);
    }
}
