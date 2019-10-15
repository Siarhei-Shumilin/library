package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ToEditCommand extends AbstractCommand {

    public ToEditCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int id = Integer.parseInt(request.getParameter("id"));
        Optional<Book> byTitle = bookService.findById(id);
        Book book = byTitle.get();
        request.setAttribute("book", book);
        return new CommandResult(Constants.EDIT, false);
    }
}
