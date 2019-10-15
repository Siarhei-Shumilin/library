package by.javatr.library.service;

import by.javatr.library.builder.impl.OrderBuilder;
import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.entity.Order;
import by.javatr.library.entity.OrderStatus;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;

import java.util.List;
import java.util.Optional;

public class OrderService {
    private DaoFactory daoFactory;

    public OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void save(Order order) throws ServiceException {
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            orderDao.save(order);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public Optional<Order> findOrderById(int id) throws ServiceException {
        Optional<Order> orderById  = Optional.empty();
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            orderById = orderDao.findOrderById(id);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return orderById;
    }

    public List<Order> findNewOrdersByUserId(int userId) throws ServiceException {
        List<Order> allOrders = null;
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            allOrders = orderDao.findByUserIdNewOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return allOrders;
    }

    public List<Order> findAllOrdersByUserId(int userId) throws ServiceException {
        List<Order> allOrders = null;
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            allOrders = orderDao.findByUserIdAllOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return allOrders;
    }

    public List<Order> findOrdersByStatus(OrderStatus orderStatus) throws ServiceException {
        List<Order> allOrders = null;
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            allOrders = orderDao.findOrdersByStatus(orderStatus);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return allOrders;
    }

    public List<Order> findAll() throws ServiceException {
        List<Order> allOrders = null;
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            allOrders = orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return allOrders;
    }

    public void updateOrderById(int id, OrderStatus orderStatus) throws ServiceException {
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            orderDao.updateOrderById(id, orderStatus);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Order> findPage(int number, int pageSize) throws ServiceException {
        List<Order> orderList = null;
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            orderList = orderDao.findPage(number, pageSize);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }

        return orderList;
    }

    public long size() throws ServiceException {
        int size = 0;
        try {
            OrderDaoImpl orderDao = daoFactory.createOrderDao();
            List<Order> all = orderDao.findAllNewOrder();
            size = all.size();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return size;
    }

    public List<Order> findAllNewOrder() throws ServiceException {
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        List<Order> activeOrders = null;
        try {
            activeOrders = orderDao.findAllNewOrder();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return activeOrders;
    }
}
