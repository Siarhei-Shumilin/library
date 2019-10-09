package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractBookCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class DeleteBookCommand extends AbstractBookCommand {

    public DeleteBookCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String title = request.getParameter("title");
        Optional<Book> bookOptional = bookService.findByTitle(title);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            bookService.delete(book);
        }
        return new CommandResult(Constants.MAIN_COMMAND, true);
    }
}
