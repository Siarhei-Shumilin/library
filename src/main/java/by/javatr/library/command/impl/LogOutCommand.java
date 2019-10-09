package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogOutCommand implements Command {

@Override
public CommandResult execute(HttpServletRequest request) {
    HttpSession session = request.getSession();
    session.invalidate();
    return new CommandResult(Constants.LOGIN, false);
}
}
