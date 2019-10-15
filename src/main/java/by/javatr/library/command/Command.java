package by.javatr.library.command;

import by.javatr.library.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;

public interface Command {
    CommandResult execute(HttpServletRequest request) throws ServiceException;
}
