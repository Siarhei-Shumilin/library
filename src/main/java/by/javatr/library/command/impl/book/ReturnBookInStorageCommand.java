package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractBookCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ReturnBookInStorageCommand extends AbstractBookCommand {

    public ReturnBookInStorageCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException, DaoException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        Optional<Book> bookOptional = bookService.findById(bookId);
        Book book = null;
        if (bookOptional.isPresent()) {
            book = bookOptional.get();
            int numberAvailableOfInstances = book.getNumberAvailableOfInstances();
            book.setNumberAvailableOfInstances(numberAvailableOfInstances + 1);
        }
        bookService.updateBook(book);

        bookService.deleteBooksIssued(bookId);
        return new CommandResult(Constants.SHOW_BOOKS_HAVE_READER_COMMAND, true);
    }
}
