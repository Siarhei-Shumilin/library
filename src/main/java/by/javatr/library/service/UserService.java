package by.javatr.library.service;

import by.javatr.library.dao.DaoFactory;
import by.javatr.library.dao.impl.UserDaoImp;
import by.javatr.library.entity.User;
import by.javatr.library.exception.DaoException;
import by.javatr.library.exception.ServiceException;
import org.apache.log4j.Logger;


import java.util.List;
import java.util.Optional;

public class UserService {
    private final static Logger LOGGER = Logger.getLogger(UserService.class);

    private DaoFactory daoFactory;

    public UserService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public Optional<User> login(String login, String password) throws ServiceException {
        try {
            UserDaoImp dao = daoFactory.createUserDao();
            return dao.findUserByLoginAndPassword(login, password);
        } catch (DaoException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ServiceException(e.getMessage(), e);
        }
    }

    public List<User> findAll() throws ServiceException {
        List<User> allUsers = null;
        try {
            UserDaoImp dao = daoFactory.createUserDao();
           allUsers = dao.findAll();
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return allUsers;
    }

    public Optional<User> findByName(String name) throws ServiceException {
        Optional<User> user = Optional.empty();
        try {
            UserDaoImp dao = daoFactory.createUserDao();
            user = dao.findByName(name);
        } catch (DaoException e) {
            throw new ServiceException(e.getMessage(), e);
        }
        return user;
    }
}
