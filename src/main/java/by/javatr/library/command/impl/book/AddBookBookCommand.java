package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractBookCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.BookValidator;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AddBookBookCommand extends AbstractBookCommand {

    public AddBookBookCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String title = request.getParameter("title");
        Optional<Book> bookOptional = bookService.findByTitle(title);
        HttpSession session = request.getSession();
        if (bookOptional.isPresent()) {
            session.setAttribute("error", true);
            return new CommandResult(Constants.MAIN_COMMAND, true);
        } else {
            String author = request.getParameter("author");
            String genre = request.getParameter("genre");
            String description = request.getParameter("description");
            Book book = new Book(title, author, genre, description, 1, 1);
            if (BookValidator.validateBook(book)){
                bookService.save(book);
                session.removeAttribute("error");
                session.removeAttribute("invalid");
            } else {
                session.setAttribute("invalid", true);
                return new CommandResult(Constants.MAIN_COMMAND, true);
            }
            return new CommandResult(Constants.MAIN_COMMAND, true);
        }
    }
}
