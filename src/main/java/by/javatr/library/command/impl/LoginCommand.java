package by.javatr.library.command.impl;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.UserService;
import by.javatr.library.util.Constants;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


public class LoginCommand implements Command {
    private final static Logger LOGGER = Logger.getLogger(LoginCommand.class);
    private static final String PARAM_NAME_LOGIN = "login";
    private static final String PARAM_NAME_PASSWORD = "password";
    private final static int PAGE_SIZE = 5;

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String password = request.getParameter(PARAM_NAME_PASSWORD);
        try {
            Optional<User> userOptional = userService.login(login, password);
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                if (Role.ADMIN.equals(user.getRole())) {
                    putAllBooksInRequest(request, user);
                    page = Constants.MAIN;
                } else if (Role.READER.equals(user.getRole())) {
                    putAllBooksInRequest(request, user);
                    page = Constants.READER;
                } else if (Role.LIBRARIAN.equals(user.getRole())) {
                    putAllOrdersInRequest(request);
                    page = Constants.LIBRARIAN;
                }
            } else {
                request.setAttribute("errorLoginPassMessage", true);
                page = Constants.LOGIN;
            }
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            page = Constants.ERROR;
        }
        return new CommandResult(page, false);
    }

    private void putAllBooksInRequest(HttpServletRequest request, User user) throws DaoException {
        String page = request.getParameter("page");
        int number = 1;
        if (page != null) {
            number = Integer.parseInt(page);
        }
        BookDaoImpl bookDao = null;
        try (ProxyConnection con = ConnectionPool.getInstance().getConnection()) {
            bookDao = new BookDaoImpl(con);
            List<Book> allBooks = null;
            if (Role.ADMIN.equals(user.getRole())) {
                allBooks = bookDao.findPage(number, PAGE_SIZE);
            } else if (Role.READER.equals(user.getRole())) {
                allBooks = bookDao.findPage(number, PAGE_SIZE);
            }
            List<Book> genreBooks = bookDao.findAll();
            for (int i = 0; i < genreBooks.size(); i++) {
                for (int j = 1; j < genreBooks.size(); j++) {
                    if (i != j) {
                        if (genreBooks.get(i).getGenre().equals(genreBooks.get(j).getGenre())) {
                            Book book = genreBooks.get(i);
                            genreBooks.remove(book);
                        }
                    }
                }
            }
            request.setAttribute("books", allBooks);
            request.setAttribute("allBooks", genreBooks);
        }

        long pageCount = (bookDao.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        request.setAttribute("count", pageCount);
        request.setAttribute("page", page);

    }

    private void putAllOrdersInRequest(HttpServletRequest request) throws DaoException {
        String page = request.getParameter("page");
        int number = 1;
        if (page != null) {
            number = Integer.parseInt(page);
        }
        OrderDaoImpl orderDao = null;
        try (ProxyConnection con = ConnectionPool.getInstance().getConnection()) {
            orderDao = new OrderDaoImpl(con);
            List<Order> all = orderDao.findPage(number, PAGE_SIZE);
            if (all.size()==0){
                request.setAttribute("book", true);
            } else {
                request.setAttribute("orders", all);
            }
        }
        long pageCount = (orderDao.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        request.setAttribute("count", pageCount);
        request.setAttribute("page", page);
    }
}
