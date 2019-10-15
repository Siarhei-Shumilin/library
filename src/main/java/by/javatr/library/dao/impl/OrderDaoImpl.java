package by.javatr.library.dao.impl;

import by.javatr.library.builder.impl.OrderBuilder;
import by.javatr.library.dao.AbstractDao;
import by.javatr.library.dao.OrderDao;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.exception.DaoException;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class OrderDaoImpl extends AbstractDao<Order, String> implements OrderDao<Order, String> {

    private final static String ADD_ORDER = "insert into orders(user_id,book_id, status, date) values (?,?,?,?)";
    private final static String FIND_All_ORDERS = "SELECT orders.id, users.name, books.title, orders.status, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id";
    private final static String FIND_All_NEW_ORDERS = "SELECT orders.id, users.name, books.title, orders.status, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE status = 'new'";
    private final static String FIND_ORDERS_BY_STATUS = "SELECT orders.id, users.name, books.title, orders.status, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE status = ?";
    private final static String FIND_NEW_ORDERS_BY_USER_ID = "SELECT orders.id, users.name, books.title, orders.status, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE user_id = ? and status = 'new'";
    private final static String FIND_ORDERS_BY_USER_ID = "SELECT orders.id, users.name, books.title, orders.status, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE user_id = ?";
    private final static String FIND_ORDERS_BY_ID = "SELECT orders.id, users.name, books.title, orders.status, orders.date, orders.book_id FROM orders INNER JOIN users ON orders.user_id=users.id INNER JOIN books on orders.book_id = books.id WHERE id = ?";
    private final static String UPDATE_ORDER_BY_ID = "UPDATE orders SET status=? WHERE id=?";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }

    @Override
    public List<Order> findAll() throws DaoException {
        return executeQuery(FIND_All_ORDERS, new OrderBuilder());
    }

    @Override
    public List<Order> findAllNewOrder() throws DaoException {
        return executeQuery(FIND_All_NEW_ORDERS, new OrderBuilder());
    }


    @Override
    public List<Order> findByUserIdNewOrders(int userId) throws DaoException {
        return executeQuery(FIND_NEW_ORDERS_BY_USER_ID, new OrderBuilder(), userId);
    }

    public List<Order> findOrdersByStatus(OrderStatus orderStatus) throws DaoException {
        String status = orderStatus.getAction();
        return executeQuery(FIND_ORDERS_BY_STATUS, new OrderBuilder(), status);
    }

    public List<Order> findByUserIdAllOrders(int userId) throws DaoException {
        return executeQuery(FIND_ORDERS_BY_USER_ID, new OrderBuilder(), userId);
    }

    public Optional<Order> findOrderById(int id) throws DaoException {
        return executeForSingleResult(FIND_ORDERS_BY_ID, new OrderBuilder(), id);
    }

    @Override
    public void save(Order order) throws DaoException {
        int userId = order.getUserId();
        int bookId = order.getBookId();
        String status = "new";
        String date = order.getDate();
        executeUpdate(ADD_ORDER, userId, bookId, status, date);
    }

    @Override
    public void updateOrderById(int id, OrderStatus orderStatus) throws DaoException {
        String status = orderStatus.getAction();
        executeUpdate(UPDATE_ORDER_BY_ID, status, id);
    }

    public List<Order> findPage(int number, int pageSize) throws DaoException {
        List<Order> all = findAllNewOrder();
        return all.stream()
                .skip((number - 1) * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
    }

    public long size() throws DaoException {
        List<Order> all = findAllNewOrder();
        return all.size();
    }
}
