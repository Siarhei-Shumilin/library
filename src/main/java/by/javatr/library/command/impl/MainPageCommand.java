package by.javatr.library.command.impl;

import by.javatr.library.command.AbstractBookCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public class MainPageCommand extends AbstractBookCommand {

    public MainPageCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        String page = null;
        try {
            if (Role.ADMIN.equals(role)) {
                putAllBooksInRequest(request);
                page = Constants.MAIN;
            } else if (Role.READER.equals(role)) {
                putAllBooksInRequest(request);
                page = Constants.READER;
            } else if (Role.LIBRARIAN.equals(role)) {
                try (ProxyConnection con = ConnectionPool.getInstance().getConnection()) {
                    OrderDaoImpl orderDao = new OrderDaoImpl(con);
                    List all = orderDao.findAllActive();
                    if (all.size()==0){
                        request.setAttribute("bookEmpty", true);
                    } else {
                        request.setAttribute("orders", all);
                    }
                    page = Constants.LIBRARIAN;
                }
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return new CommandResult(page, false);
    }
}
