package by.javatr.library.command;

import by.javatr.library.command.impl.book.*;
import by.javatr.library.command.impl.common.*;
import by.javatr.library.command.impl.order.*;
import by.javatr.library.command.impl.user.ShowUserOrdersCommand;
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
                return new LoginCommand(new BookService(daoFactory), new OrderService(daoFactory), new UserService(daoFactory));
            case "addBook":
                return new AddBookCommand(new BookService(daoFactory));
            case "delete":
                return new DeleteCommand(new BookService(daoFactory));
            case "order":
                return new OrderBookCommand(new BookService(daoFactory), new OrderService(daoFactory));
            case "to_edit":
                return new ToEditCommand(new BookService(daoFactory));
            case "language":
                return new LanguageCommand();
            case "logout":
                return new LogOutCommand();
            case "main":
                return new MainPageCommand(new BookService(daoFactory), new OrderService(daoFactory));
            case "edit":
                return new EditBookCommand(new BookService(daoFactory));
            case "searchBook":
                return new SearchBookCommand(new BookService(daoFactory));
            case "showUserOrders":
                return new ShowUserOrdersCommand(new OrderService(daoFactory));
            case "searchOrder":
                return new SearchOrderCommand(new OrderService(daoFactory), new UserService(daoFactory));
            case "cancelOrder":
                return new CancelOrderCommand(new BookService(daoFactory), new OrderService(daoFactory));
            case "ShowBooksByGenre":
                return new ShowBooksByGenreCommand(new BookService(daoFactory));
            case "showUsers":
                return new ShowUsersCommand(new UserService(daoFactory));
            case "giveBook":
                return new GiveOrderedBookCommand(new BookService(daoFactory), new OrderService(daoFactory));
            case "showBooksHaveReader":
                return new ShowBooksHaveReaderCommand(new BookService(daoFactory), new OrderService(daoFactory));
            case "returnBook":
                return new ReturnInStorageCommand(new BookService(daoFactory), new OrderService(daoFactory));
            default:
                throw new IllegalArgumentException("Unknown command");
        }
    }
}
