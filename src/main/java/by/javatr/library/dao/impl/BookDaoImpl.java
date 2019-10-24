package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.BookBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.BookDao;
import by.javatr.library.entity.Book;
import by.javatr.library.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BookDaoImpl extends AbstractDao<Book, String> implements BookDao<Book, String> {

    private final static String ADD_BOOK = "insert into books(title,author,genre,description,number_instances, number_available_instances) values (?,?,?,?,?,?)";
    private final static String FIND_All_BOOKS = "SELECT * FROM books";
    private final static String FIND_BOOK_BY_TITLE = "SELECT * FROM books WHERE title = ?";
    private final static String FIND_BOOK_BY_ID = "SELECT * FROM books WHERE id = ?";
    private final static String FIND_BOOK_BY_GENRE = "SELECT * FROM books WHERE genre = ?";
    private final static String UPDATE_BOOK = "UPDATE books SET title=?, author=?, genre=?,description=?,number_instances=?, number_available_instances = ? WHERE id=?";
    private final static String DELETE_BOOK_BY_TITLE = "DELETE FROM books WHERE title = ?";

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
            String title = book.getTitle();
            String author = book.getAuthor();
            String genre = book.getGenre();
            String description = book.getDescription();
            int numberOfInstances = book.getNumberOfInstances();
            int numberOfAvailableInstances = book.getNumberOfInstances();
            executeUpdate(ADD_BOOK, title, author, genre, description, numberOfInstances, numberOfAvailableInstances);
    }

    @Override
    public void removeByTitle(String title) throws DaoException {
        executeUpdate(DELETE_BOOK_BY_TITLE, title);
    }

    @Override
    public void updateBook(Book book) throws DaoException {
        String title = book.getTitle();
        String author = book.getAuthor();
        String genre = book.getGenre();
        String description = book.getDescription();
        int numberOfInstances = book.getNumberOfInstances();
        int numberOfAvailableInstances = book.getNumberAvailableOfInstances();
        int id = book.getId();
        executeUpdate(UPDATE_BOOK, title, author, genre, description, numberOfInstances, numberOfAvailableInstances, id);
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
