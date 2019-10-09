package by.javatr.library.command.impl.user;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.UserService;
import by.javatr.library.util.Constants;


import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowUsersCommand implements Command {
    private UserService userService;

    public ShowUsersCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException, DaoException {
        List<User> users = userService.findAll();
        request.setAttribute("users", users);
        return new CommandResult(Constants.USERS, false);
    }
}
