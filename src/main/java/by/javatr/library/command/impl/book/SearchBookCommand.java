package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

public class SearchBookCommand extends AbstractCommand {

    public SearchBookCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        String title = request.getParameter("title");
        Optional<Book> bookOptional = bookService.findByTitle(title);
        if (bookOptional.isPresent()) {
            List<Book> all = new ArrayList<>();
            all.add(bookOptional.get());
            request.setAttribute("books", all);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Role role = user.getRole();
            if (Role.READER.equals(role)) {
                List<Book> bookList = bookService.findAll();
                Set<String> listGenre = new HashSet<>();
                for (Book book : bookList) {
                    listGenre.add(book.getGenre());
                }
                request.setAttribute("genre", listGenre);
                page = Constants.READER;
            } else if (Role.ADMIN.equals(role)) {
                page = Constants.MAIN;
            }
        } else {
            request.setAttribute("errorSearch", true);
            page = Constants.MAIN_COMMAND;
        }
        return new CommandResult(page, false);
    }
}
