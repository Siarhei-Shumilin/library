package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractBookCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowBooksByGenreBookCommand extends AbstractBookCommand {

    public ShowBooksByGenreBookCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException, DaoException {
        String genre = request.getParameter("genre");
        List<Book> books = bookService.findByGenre(genre);
        request.setAttribute("books", books);
        return new CommandResult(Constants.READER, false);
    }
}
