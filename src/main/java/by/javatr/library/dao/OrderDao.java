package by.javatr.library.dao;

import by.javatr.library.entity.Order;
import by.javatr.library.exception.DaoException;

import java.util.List;

public interface OrderDao<T, K> extends Dao<T,K> {
    void save(Order order) throws DaoException;
    List<Order> findAllActive() throws DaoException;
    List<Order> findByUserIdActiveOrders(int userId) throws DaoException;
    void updateOrderById(int id) throws DaoException;
}
