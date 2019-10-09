package by.javatr.library.dao;

import by.javatr.library.builder.Builder;
import by.javatr.library.exception.DaoException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class AbstractDao<T, K> implements Dao<T, K> {
    private final static Logger LOGGER = Logger.getLogger(AbstractDao.class);

    private Connection connection;

    public AbstractDao(Connection connection) {
        this.connection = connection;
    }

    protected List<T> executeQuery(String query, Builder<T> builder, Object... parameters) throws DaoException {
        List<T> entities = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            setParametersPreparedStatement(preparedStatement, parameters);
            ResultSet resultSet = preparedStatement.executeQuery();
            entities = new ArrayList<>();
            while (resultSet.next()) {
                T entity = builder.build(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    protected Optional<T> executeForSingleResult(String query, Builder<T> builder, Object... parameters) throws DaoException {
        List<T> items = executeQuery(query, builder, parameters);
        if (items.size() == 1) {
            return Optional.of(items.get(0));
        } else {
            return Optional.empty();
        }
    }

    private void setParametersPreparedStatement(PreparedStatement preparedStatement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            preparedStatement.setObject(i+1, parameters[i]);
        }
    }

    protected PreparedStatement executeUpdate(String query) throws DaoException {
        PreparedStatement preparedStatement = null;
        try{
            preparedStatement = connection.prepareStatement(query);
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
            throw new DaoException(e.getMessage(), e);
        }
        return preparedStatement;
    }

    public Connection getConnection() {
        return connection;
    }

}
