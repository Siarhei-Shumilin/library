package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractBookCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Role;
import by.javatr.library.entity.User;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchBookBookCommand extends AbstractBookCommand {

    public SearchBookBookCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String page = null;
        String title = request.getParameter("title");
        Optional<Book> bookOptional = bookService.findByTitle(title);
        List<Book> all = new ArrayList<>();
        if (bookOptional.isPresent()) {
            all.add(bookOptional.get());
        } else {
            request.setAttribute("errorSearch", true);
        }
        request.setAttribute("books", all);
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Role role = user.getRole();
        if (Role.READER.equals(role)) {
            page = Constants.READER;

        } else if (Role.ADMIN.equals(role)) {
            page = Constants.MAIN;
            session.removeAttribute("error");
            session.removeAttribute("invalid");
        }
        return new CommandResult(page, false);
    }
}
