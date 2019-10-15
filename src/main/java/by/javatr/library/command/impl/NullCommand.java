package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;

public class NullCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request)  {
        return new CommandResult(Constants.LOGIN, false);
    }
}
