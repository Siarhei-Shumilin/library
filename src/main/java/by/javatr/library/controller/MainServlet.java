package by.javatr.library.controller;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandFactory;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private final static Logger LOGGER = Logger.getLogger(MainServlet.class);
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("command");
        CommandResult commandResult = null;
        ConnectionPool connectionPool = ConnectionPool.getInstance();
            try (ProxyConnection connection = connectionPool.getConnection()) {
                DaoFactory daoFactory = new DaoFactory(connection);
                CommandFactory commandFactory = new CommandFactory(daoFactory);
                Command command = commandFactory.createCommand(action);

                User user = (User) request.getAttribute("user");
                if (user != null) {
                    response.addCookie(new Cookie("userName", user.getName()));
                }
                commandResult = command.execute(request);
            } catch (ServiceException | DaoException e) {
                LOGGER.error(e.getMessage(), e);
                request.setAttribute("error", e.getMessage());
                commandResult = new CommandResult(Constants.ERROR, false);
            }

            if (commandResult.isRedirect()) {
                response.sendRedirect(request.getContextPath() + commandResult.getPage());
            } else {
                request.getRequestDispatcher(commandResult.getPage()).forward(request, response);
            }
        }
}
