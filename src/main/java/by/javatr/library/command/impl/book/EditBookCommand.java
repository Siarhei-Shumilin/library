package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class EditBookCommand extends AbstractCommand {

    public EditBookCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException{
        int id = Integer.parseInt(request.getParameter("id"));
        Book book = null;
        Optional<Book> bookOptional = bookService.findById(id);
        int numberNotAvailableBooks = 0;
        int oldNumberOfInstances = 0;
        if (bookOptional.isPresent()){
            book = bookOptional.get();
            oldNumberOfInstances = book.getNumberOfInstances();
            int oldNumberAvailableOfInstances = book.getNumberAvailableOfInstances();
            numberNotAvailableBooks = oldNumberOfInstances - oldNumberAvailableOfInstances;
        }
        String bookTitle = request.getParameter("title");
        String author = request.getParameter("author");
        String genre =request.getParameter("genre");
        String description = request.getParameter("description");
        int numberOfInstances = Integer.parseInt(request.getParameter("numberOfInstances"));
        if(numberOfInstances<numberNotAvailableBooks){
            numberOfInstances = numberNotAvailableBooks;
        }
        int numberAvailableOfInstances = numberOfInstances - numberNotAvailableBooks;
        book = new Book(id, bookTitle, author, genre, description, numberOfInstances, numberAvailableOfInstances);
        bookService.updateBook(book);
        return new CommandResult(Constants.MAIN_COMMAND, true);
    }
}
