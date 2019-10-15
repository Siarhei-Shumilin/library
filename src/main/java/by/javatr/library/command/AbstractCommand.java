package by.javatr.library.command;

import by.javatr.library.entity.Book;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

public abstract class AbstractCommand implements Command {
    private final static int PAGE_SIZE = 5;
    protected BookService bookService;
    protected OrderService orderService;

    public AbstractCommand(BookService bookService, OrderService orderService) {
        this.bookService = bookService;
        this.orderService = orderService;
    }

    public AbstractCommand(BookService bookService) {
        this.bookService = bookService;
    }

    public AbstractCommand(OrderService orderService) {
        this.orderService = orderService;
    }

    protected void putAllBooksInRequest(HttpServletRequest request) throws ServiceException {
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

    protected void putAllOrdersInRequest(HttpServletRequest request) throws ServiceException {
        String page = request.getParameter("page");
        int number = 1;
        if (page != null) {
            number = Integer.parseInt(page);
        }
        List<Order> all = orderService.findPage(number, PAGE_SIZE);
        if (all.size() == 0) {
            request.setAttribute("bookEmpty", true);
        } else {
            request.setAttribute("orders", all);
        }
        long pageCount = (orderService.size() + PAGE_SIZE - 1) / PAGE_SIZE;
        request.setAttribute("count", pageCount);
        request.setAttribute("page", page);
    }

    protected void returnBook(int bookId) throws ServiceException {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            int numberAvailableOfInstances = book.getNumberAvailableOfInstances();
            book.setNumberAvailableOfInstances(numberAvailableOfInstances + 1);
            bookService.updateBook(book);
        }
    }

    protected void giveBook(int bookId) throws ServiceException {
        Optional<Book> optionalBook = bookService.findById(bookId);
        if (optionalBook.isPresent()){
            Book book = optionalBook.get();
            int numberAvailableOfInstances = book.getNumberAvailableOfInstances();
            book.setNumberAvailableOfInstances(numberAvailableOfInstances - 1);
            bookService.updateBook(book);
        }
    }
}