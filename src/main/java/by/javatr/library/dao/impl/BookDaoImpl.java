package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.BookBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.BookDao;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookDaoImpl extends AbstractDao<Book, String> implements BookDao<Book, String> {
    private final static Logger LOGGER = Logger.getLogger(BookDaoImpl.class);

    private final static String ADD_BOOK = "insert into books(title,author,genre,description,number_instances, number_available_instances) values (?,?,?,?,?,?)";
    private final static String ADD_BOOK_IN_ISSUED = "insert into books_issued(book_id) values (?)";
    private final static String FIND_BOOK_IN_ISSUED = "SELECT books.id, books.title, books.author, books.genre, books.description, books.number_instances, books.number_available_instances FROM books_issued INNER JOIN books ON books_issued.book_id=books.id";
    private final static String FIND_All_BOOKS = "SELECT * FROM books";
    private final static String FIND_BOOK_BY_TITLE = "SELECT * FROM books WHERE title = ?";
    private final static String FIND_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
    private final static String FIND_BOOK_BY_GENRE = "SELECT * FROM books WHERE genre = ?";
    private final static String UPDATE_BOOK = "UPDATE books SET title=?, author=?, genre=?,description=?,number_instances=?, number_available_instances = ? WHERE id=?";
    private final static String DELETE_BOOK_BY_TITLE = "DELETE FROM books WHERE title = ?";
    private final static String DELETE_BOOK_FROM_ISSUED = "DELETE FROM books_issued WHERE book_id = ?";

    public BookDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<Book> findByTitle(String title) throws DaoException {
        return executeForSingleResult(FIND_BOOK_BY_TITLE, new BookBuilder(), title);
    }

    @Override
    public List<Book> findByGenre(String genre) throws DaoException {
        return executeQuery(FIND_BOOK_BY_GENRE, new BookBuilder(), genre);
    }

    @Override
    public List<Book> findAll() throws DaoException {
        return executeQuery(FIND_All_BOOKS, new BookBuilder());
    }

    @Override
    public Optional<Book> findById(int id) throws DaoException {
        return executeForSingleResult(FIND_BOOK_BY_ID, new BookBuilder(), id);
    }

    @Override
    public void save(Book book) throws DaoException {
        try {
            PreparedStatement preparedStatement = executeUpdate(ADD_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setInt(5, book.getNumberOfInstances());
            preparedStatement.setInt(6, book.getNumberOfInstances());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    public void saveBookInIssued(Book book) throws DaoException {
        try {
            PreparedStatement preparedStatement = executeUpdate(ADD_BOOK_IN_ISSUED);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    public List<Book> findAllIssuedBooks() throws DaoException {
        return executeQuery(FIND_BOOK_IN_ISSUED, new BookBuilder());
    }

    @Override
    public void removeByTitle(String title) throws DaoException {
        try {
            PreparedStatement preparedStatement = executeUpdate(DELETE_BOOK_BY_TITLE);
            preparedStatement.setString(1, title);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void removeBooksIssued(int bookId) throws DaoException {
        try {
            PreparedStatement preparedStatement = executeUpdate(DELETE_BOOK_FROM_ISSUED);
            preparedStatement.setInt(1, bookId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public void updateBook(Book book) throws DaoException {
        try {
            PreparedStatement preparedStatement = executeUpdate(UPDATE_BOOK);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setString(3, book.getGenre());
            preparedStatement.setString(4, book.getDescription());
            preparedStatement.setInt(5, book.getNumberOfInstances());
            preparedStatement.setInt(6, book.getNumberAvailableOfInstances());
            preparedStatement.setInt(7, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
    }

    @Override
    public List<Book> findPage(int number, int pageSize) throws DaoException {
        List<Book> all = findAll();
        return all.stream()
                .skip((number - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }


    @Override
    public long size() throws DaoException {
        List<Book> all = findAll();
        return all.size();
    }
}
