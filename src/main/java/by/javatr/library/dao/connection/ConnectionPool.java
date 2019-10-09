package by.javatr.library.dao.connection;

import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private final static Logger LOGGER = Logger.getLogger(ConnectionPool.class);

    private LinkedBlockingQueue<ProxyConnection> connectionQueue;
    private final static String URL = DbPropertyManager.getProperty("url");
    private final static String USER = DbPropertyManager.getProperty("user");
    private final static String PASSWORD = DbPropertyManager.getProperty("password");
    private final static int POOL_SIZE = Integer.parseInt(DbPropertyManager.getProperty("poolSize"));
    private static final ConnectionPool INSTANCE = new ConnectionPool(POOL_SIZE);

    private ConnectionPool(int poolSize) {
        connectionQueue = new LinkedBlockingQueue<>(poolSize);
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        } catch (SQLException e) {
            LOGGER.fatal("ConnectionPool(int poolSize)", e);
            throw new RuntimeException(e);
        }
        for (int i = 0; i < poolSize; i++) {
            connectionQueue.offer(createConnection());
        }
    }

    private ProxyConnection createConnection() {
        ProxyConnection proxyConnection = null;
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            proxyConnection = new ProxyConnection(connection);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return proxyConnection;
    }

    public static ConnectionPool getInstance() {
        return INSTANCE;
    }

    public ProxyConnection getConnection() throws DaoException {
        ProxyConnection connection;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return connection;
    }

    /*package private*/ void closeConnection(ProxyConnection connection) {
        try {
            connectionQueue.put(connection);
        } catch (InterruptedException e) {
            LOGGER.error(e.getMessage(), e);
            connectionQueue.offer(createConnection());
        }
    }
}

