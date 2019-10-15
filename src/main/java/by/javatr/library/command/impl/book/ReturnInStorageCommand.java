package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class ReturnInStorageCommand extends AbstractCommand {

    public ReturnInStorageCommand(BookService bookService, OrderService orderService) {
        super(bookService, orderService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String title = request.getParameter("title");
        Optional<Book> bookOptional = bookService.findByTitle(title);
        Book book = null;
        if (bookOptional.isPresent()) {
            book = bookOptional.get();
            int numberAvailableOfInstances = book.getNumberAvailableOfInstances();
            book.setNumberAvailableOfInstances(numberAvailableOfInstances + 1);
        }
        bookService.updateBook(book);
        orderService.updateOrderById(orderId, OrderStatus.CLOSE);
        return new CommandResult(Constants.SHOW_BOOKS_HAVE_READER_COMMAND, true);
    }
}
