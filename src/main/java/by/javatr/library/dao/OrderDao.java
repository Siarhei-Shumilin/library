package by.javatr.library.dao;

import by.javatr.library.entity.Order;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.exception.DaoException;

import java.util.List;

public interface OrderDao<T, K> extends Dao<T,K> {
    void save(Order order) throws DaoException;
    List<Order> findAllNewOrder() throws DaoException;
    List<Order> findByUserIdNewOrders(int userId) throws DaoException;
    void updateOrderById(int id, OrderStatus orderStatus) throws DaoException;
}
