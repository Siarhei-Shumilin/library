package by.javatr.library.dao;

import by.javatr.library.dao.impl.BookDaoImpl;
import by.javatr.library.dao.impl.OrderDaoImpl;
import by.javatr.library.dao.impl.UserDaoImp;

import java.sql.Connection;

public class DaoFactory {
    private Connection connection;

    public DaoFactory(Connection connection) {
        this.connection = connection;
    }

    public UserDaoImp createUserDao(){
        return new UserDaoImp(connection);
    }

    public BookDaoImpl createBookDao(){
        return new BookDaoImpl(connection);
    }

    public OrderDaoImpl createOrderDao(){
        return new OrderDaoImpl(connection);
    }
}
