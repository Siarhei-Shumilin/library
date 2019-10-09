package by.javatr.library.command.impl.order;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.UserDaoImp;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

public class SearchOrderCommand implements Command {
    private OrderService orderService;

    public SearchOrderCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException, DaoException {
        User user = null;
        try (ProxyConnection connection = ConnectionPool.getInstance().getConnection()) {
            UserDaoImp userDaoImp = new UserDaoImp(connection);
            String name = request.getParameter("name");
            Optional<User> userOptional = userDaoImp.findByName(name);
            if (userOptional.isPresent()) {
                user = userOptional.get();
            } else {
                request.setAttribute("bookEmpty", true);
                return new CommandResult(Constants.MAIN_COMMAND, false);
            }
        }
        List<Order> orderByUserId = orderService.findActiveOrdersByUserId(user.getId());
        if (orderByUserId.size() == 0) {
            request.setAttribute("bookEmpty", true);
        }
        request.setAttribute("orders", orderByUserId);
        return new CommandResult(Constants.LIBRARIAN, false);
    }
}
