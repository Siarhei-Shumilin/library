package by.javatr.library.service;

import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.entity.Book;
import by.javatr.library.entity.Order;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderService {
    private final static Logger LOGGER = Logger.getLogger(OrderService.class);
    private DaoFactory daoFactory;

    public OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void save(Order order) throws ServiceException {
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        try {
            orderDao.save(order);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Order> findActiveOrdersByUserId(int userId) throws ServiceException {
        List<Order> allOrders = null;
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        try {
            allOrders = orderDao.findByUserIdActiveOrders(userId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return allOrders;
    }

    public List<Order> findAllOrdersByUserId(int userId) throws ServiceException {
        List<Order> allOrders = null;
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        try {
            allOrders = orderDao.findByUserIdAllOrders(userId);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return allOrders;
    }

    public List<Order> findAll() throws ServiceException {
        List<Order> allOrders = null;
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        try {
            allOrders = orderDao.findAll();
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
        return allOrders;
    }

    public void updateOrderById(int id) throws ServiceException {
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        try {
            orderDao.updateOrderById(id);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<Order> findPage(int number, int pageSize) throws ServiceException {
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        List<Order> orderList = null;
        try {
            orderList = orderDao.findPage(number, pageSize);
        } catch (DaoException e){
            throw new ServiceException(e.getMessage(), e);
        }

        return orderList;
    }

    public long size() throws DaoException {
        OrderDaoImpl orderDao = daoFactory.createOrderDao();
        List<Order> all = orderDao.findAll();
        return all.size();
    }
}
