package by.javatr.library.command.impl;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class MainPageCommand extends AbstractCommand {

    public MainPageCommand(BookService bookService, OrderService orderService) {
        super(bookService, orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        String page = null;
        if (Role.ADMIN.equals(role)) {
            putAllBooksInRequest(request);
            page = Constants.MAIN;
        } else if (Role.READER.equals(role)) {
            putAllBooksInRequest(request);
            page = Constants.READER;
        } else if (Role.LIBRARIAN.equals(role)) {
            putAllOrdersInRequest(request);
            page = Constants.LIBRARIAN;
        }
        return new CommandResult(page, false);
    }
}
