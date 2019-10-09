package by.javatr.library.command.impl.order;

import by.javatr.library.command.AbstractOrderCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class OrderBookCommand extends AbstractOrderCommand {

    public OrderBookCommand(OrderService orderService) {
        super(orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getId();
        int bookId = Integer.parseInt(request.getParameter("id"));
        String date = request.getParameter("date");
        Order order = new Order.Builder()
                .buildUserId(userId)
                .buildBookId(bookId)
                .buildDate(date)
                .build();
        orderService.save(order);
        giveBook(bookId);
        return new CommandResult(Constants.MAIN_COMMAND, true);
    }
}
