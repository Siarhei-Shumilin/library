package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

public class ShowBooksByGenreCommand extends AbstractCommand {

    public ShowBooksByGenreCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String genre = request.getParameter("genre");
        List<Book> booksByGenre = bookService.findByGenre(genre);
        Set<String> setGenres = bookService.getGenres();
        request.setAttribute("genre", setGenres);
        request.setAttribute("books", booksByGenre);
        return new CommandResult(Constants.READER, false);
    }
}
