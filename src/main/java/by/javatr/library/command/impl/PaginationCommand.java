package by.javatr.library.command.impl;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



public class PaginationCommand extends AbstractCommand {

    public PaginationCommand(BookService bookService, OrderService orderService) {
        super(bookService, orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        CommandResult commandResult = null;
        if (Role.ADMIN.equals(user.getRole())){
            putAllBooksInRequest(request);
            commandResult = new CommandResult(Constants.MAIN, false);
        } else if (Role.READER.equals(user.getRole())){
            putAllBooksInRequest(request);
            commandResult =  new CommandResult(Constants.READER, false);
        } else if (Role.LIBRARIAN.equals(user.getRole())){
            putAllOrdersInRequest(request);
            commandResult =  new CommandResult(Constants.LIBRARIAN, false);
        }
        return commandResult;
    }
}
