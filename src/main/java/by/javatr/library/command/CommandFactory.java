package by.javatr.library.command;

import by.javatr.library.command.impl.*;
import by.javatr.library.command.impl.book.*;
import by.javatr.library.command.impl.order.*;
import by.javatr.library.command.impl.user.ShowUsersCommand;
import by.javatr.library.dao.DaoFactory;
import by.javatr.library.service.BookService;
import by.javatr.library.service.OrderService;
import by.javatr.library.service.UserService;



public class CommandFactory {
    private DaoFactory daoFactory;

    public CommandFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Command createCommand(String action) {
        if (action==null){
            return new NullCommand();
        }

        switch (action) {
            case "login":
                return new LoginCommand(new UserService(daoFactory));
            case "addBook":
                return new AddBookBookCommand(new BookService(daoFactory));
            case "delete":
                return new DeleteBookCommand(new BookService(daoFactory));
            case "order":
                return new OrderBookCommand(new OrderService(daoFactory));
            case "to_edit":
                return new ToEditBookCommand(new BookService(daoFactory));
            case "language":
                return new LanguageCommand();
            case "logout":
                return new LogOutCommand();
            case "main":
                return new MainPageCommand(new BookService(daoFactory));
            case "edit":
                return new EditBookBookCommand(new BookService(daoFactory));
            case "searchBook":
                return new SearchBookBookCommand(new BookService(daoFactory));
            case "showUserOrders":
                return new ShowUserOrdersCommand(new OrderService(daoFactory));
            case "searchOrder":
                return new SearchOrderCommand(new OrderService(daoFactory));
            case "cancelOrder":
                return new CancelOrderCommand(new OrderService(daoFactory));
            case "pagination":
                return new PaginationCommand(new BookService(daoFactory));
            case "ShowBooksByGenre":
                return new ShowBooksByGenreBookCommand(new BookService(daoFactory));
            case "showUsers":
                return new ShowUsersCommand(new UserService(daoFactory));
            case "giveBook":
                return new GiveOrderedBookCommand(new OrderService(daoFactory));
            case "showBooksHaveReader":
                return new ShowBooksHaveReaderCommand();
            case "returnBook":
                return new ReturnBookInStorageCommand(new BookService(daoFactory));
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
