package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.OrderBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.OrderDao;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Order;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderDaoImpl extends AbstractDao<Order, String> implements OrderDao<Order, String> {
    private final static Logger LOGGER = Logger.getLogger(OrderDaoImpl.class);

    private final static String ADD_ORDER = "insert into orders(user_id,book_id, isActive, date) values (?,?,?,?)";
    private final static String FIND_All_ORDERS = "SELECT orders.id, users.name, books.title, orders.isActive, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id";
    private final static String FIND_All_ACTIVE_ORDERS = "SELECT orders.id, users.name, books.title, orders.isActive, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE isActive = ?";
    private final static String FIND_All_ACTIVE_ORDERS_BY_USER_ID = "SELECT orders.id, users.name, books.title, orders.isActive, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE user_id = ? and isActive = ?";
    private final static String FIND_ORDERS_BY_USER_ID = "SELECT orders.id, users.name, books.title, orders.isActive, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE user_id = ?";
    private final static String UPDATE_ORDER_BY_ID = "UPDATE orders SET isActive=? WHERE id=?";
    private final static String ADD_BOOK_IN_ISSUED = "insert into books_issued(book_id) values (?)";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return executeQuery(FIND_All_ORDERS, new OrderBuilder());
    }

    @Override
    public List<Order> findAllActive() throws DaoException {
        boolean active = true;
        return executeQuery(FIND_All_ACTIVE_ORDERS, new OrderBuilder(), active);
    }

    @Override
    public List<Order> findByUserIdActiveOrders(int userId) throws DaoException {
        boolean active = true;
        return executeQuery(FIND_All_ACTIVE_ORDERS_BY_USER_ID, new OrderBuilder(), userId, active);
    }
    public List<Order> findByUserIdAllOrders(int userId) throws DaoException {
        return executeQuery(FIND_ORDERS_BY_USER_ID, new OrderBuilder(), userId);
    }

    @Override
    public void save(Order order) throws DaoException {
//        try {
//            PreparedStatement preparedStatement = executeUpdate(ADD_ORDER);
//            preparedStatement.setInt(1, order.getUserId());
//            preparedStatement.setInt(2, order.getBookId());
//            preparedStatement.setBoolean(3, true);
//            preparedStatement.setString(4, order.getDate());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
//            throw new DaoException(e.getMessage(), e);
//        }
    }

    public void saveBookInIssued(Book book) throws DaoException {
//        try {
//            PreparedStatement preparedStatement = executeUpdate(ADD_BOOK_IN_ISSUED);
//            preparedStatement.setInt(1, book.getId());
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            LOGGER.error(e.getMessage(), e);
//            throw new DaoException(e.getMessage(), e);
//        }
    }

    @Override
    public void updateOrderById(int id) throws DaoException {
//        try {
//            PreparedStatement preparedStatement = executeUpdate(UPDATE_ORDER_BY_ID);
//            preparedStatement.setBoolean(1, false);
//            preparedStatement.setInt(2, id);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            throw new DaoException(e.getMessage(), e);
//        }
    }


    public List<Order> findPage(int number, int pageSize) throws DaoException {
        List<Order> all = findAllActive();
        return all.stream()
                .skip((number - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public long size() throws DaoException {
        List<Order> all = findAllActive();
        return all.size();
    }
}
