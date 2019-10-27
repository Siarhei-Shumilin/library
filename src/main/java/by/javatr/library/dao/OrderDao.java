package by.javatr.library.dao;

import by.javatr.library.entity.Order;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.exception.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao<T, K> extends Dao<T,K> {
    void save(Order order) throws DaoException;
    List<Order> findAllNewOrder() throws DaoException;
    List<Order> findByUserIdNewOrders(int userId) throws DaoException;
    void updateOrderById(int id, OrderStatus orderStatus) throws DaoException;
    List<Order> findOrdersByStatus(OrderStatus orderStatus) throws DaoException;
    List<Order> findByUserIdAllOrders(int userId) throws DaoException;
    Optional<Order> findOrderById(int id) throws DaoException;
    List<Order> findPage(int number, int pageSize) throws DaoException;
    long size() throws DaoException;
}
