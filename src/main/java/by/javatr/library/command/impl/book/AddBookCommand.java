package by.javatr.library.command.impl.book;

import by.javatr.library.command.AbstractCommand;
import by.javatr.library.command.CommandResult;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.service.BookService;
import by.javatr.library.util.BookValidator;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Optional;

public class AddBookCommand extends AbstractCommand {

    public AddBookCommand(BookService bookService) {
        super(bookService);
    }

    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException {
        String title = request.getParameter("title");
        Optional<Book> bookOptional = bookService.findByTitle(title);
        CommandResult commandResult;
        if (bookOptional.isPresent()) {
            request.setAttribute("error", true);
            commandResult = new CommandResult(Constants.MAIN_COMMAND, false);
        } else {
            String author = request.getParameter("author");
            String genre = request.getParameter("genre");
            String description = request.getParameter("description");
            String number = request.getParameter("numberOfInstances");
            boolean validateTitle = BookValidator.validate(title, Constants.GENERAL_PATTERN);
            boolean validateAuthor = BookValidator.validate(author, Constants.GENERAL_PATTERN);
            boolean validateGenre = BookValidator.validate(genre, Constants.GENERAL_PATTERN);
            boolean validateDescription = BookValidator.validate(description, Constants.DESCRIPTION_PATTERN);
            boolean validateNumberOfInstances = BookValidator.validate(number, Constants.NUMBER_OF_INSTANCES_PATTERN);
            if (validateTitle && validateAuthor && validateGenre && validateDescription && validateNumberOfInstances ){
                int numberOfInstances = Integer.parseInt(number);
                Book book = new Book(title, author, genre, description, numberOfInstances, numberOfInstances);
                bookService.save(book);
                commandResult = new CommandResult(Constants.MAIN_COMMAND, true);
            } else {
                request.setAttribute("invalid", true);
                commandResult = new CommandResult(Constants.MAIN_COMMAND, false);
            }
        }
        return commandResult;
    }

}
