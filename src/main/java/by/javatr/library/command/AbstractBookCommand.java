package by.javatr.library.command;

import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

public abstract class AbstractBookCommand implements Command {
    private final static int PAGE_SIZE = 5;
    protected BookService bookService;

    public AbstractBookCommand(BookService bookService) {
        this.bookService = bookService;
    }

    protected void putAllBooksInRequest(HttpServletRequest request) throws DaoException, ServiceException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String page = request.getParameter("page");
        int number = 1;
        if (page != null) {
            number = Integer.parseInt(page);
        }
        List<Book> allBooks = bookService.findPage(number, PAGE_SIZE);
        if (Role.READER.equals(user.getRole())) {
            List<Book> booksGenre = bookService.findAll();
            for (int i = 0; i < booksGenre.size(); i++) {
                for (int j = 1; j < booksGenre.size(); j++) {
                    if (i != j) {
                        if (booksGenre.get(i).getGenre().equals(booksGenre.get(j).getGenre())) {
                            Book book = booksGenre.get(i);
                            booksGenre.remove(book);
                        }
                    }
                }
            }
            request.setAttribute("allBooks", booksGenre);
        }
        request.setAttribute("books", allBooks);
        long pageCount = (bookService.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        request.setAttribute("count", pageCount);
        request.setAttribute("page", page);

    }

    protected void putAllOrdersInRequest(HttpServletRequest request) throws DaoException {
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
