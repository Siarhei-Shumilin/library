package by.javatr.library.command.impl;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.service.UserService;
import by.javatr.library.util.Constants;
import by.javatr.library.util.MD5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;


public class LoginCommand extends AbstractCommand {

    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";

    private UserService userService;

    public LoginCommand(BookService bookService, OrderService orderService, UserService userService) {
        super(bookService, orderService);
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = MD5.md5(request.getParameter(PARAM_NAME_PASSWORD));
        Optional<User> userOptional = userService.login(login, password);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            if (Role.ADMIN.equals(user.getRole())) {
                putAllBooksInRequest(request);
                page = Constants.MAIN;
            } else if (Role.READER.equals(user.getRole())) {
                putAllBooksInRequest(request);
                page = Constants.READER;
            } else if (Role.LIBRARIAN.equals(user.getRole())) {
                putAllOrdersInRequest(request);
                page = Constants.LIBRARIAN;
            }
        } else {
            request.setAttribute("errorLoginPassMessage", true);
            page = Constants.LOGIN;
        }
        return new CommandResult(page, false);
    }
}
