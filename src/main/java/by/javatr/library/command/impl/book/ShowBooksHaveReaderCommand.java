package by.javatr.library.command.impl.book;

import by.javatr.library.command.Command;
import by.javatr.library.command.CommandResult;
import by.javatr.library.dao.connection.ConnectionPool;
import by.javatr.library.dao.connection.ProxyConnection;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import by.javatr.library.util.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;


public class ShowBooksHaveReaderCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request) throws ServiceException, DaoException {
        try(ProxyConnection connection = ConnectionPool.getInstance().getConnection()){
            BookDaoImpl bookDao = new BookDaoImpl(connection);
            List<Book> booksIssue = bookDao.findAllIssuedBooks();
            if (booksIssue.size()==0){
                request.setAttribute("booksEmpty", true);
            } else {
                request.setAttribute("books", booksIssue);
            }
        }
        return new CommandResult(Constants.BOOK, false);
    }
}
