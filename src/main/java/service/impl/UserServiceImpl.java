package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import entities.User;
import exceptions.AddEntityException;
import exceptions.FindEntityException;
import org.apache.log4j.Logger;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = new UserDAOImpl();
    private final Logger logger = Logger.getLogger(UserServiceImpl.class);

    @Override
    public boolean create(User user) throws AddEntityException {
        user.setPassword(getHashPassword(user.getPassword()));
        userDAO.add(user);
        return true;
    }

    @Override
    public User findById(Long id) {
        return userDAO.findById(id).orElse(null);
    }

    @Override
    public User findByLogin(String login, String password) throws FindEntityException {
        User user = userDAO.findByLogin(login).orElse(null);
        if (user != null
                && getHashPassword(password).equals(user.getPassword())) {
            return user;
        } else {
            throw new FindEntityException("Логин или пароль не совпал");
        }
    }

    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Override
    public void update(User user) {
        userDAO.update(user);
    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
    }

    private String getHashPassword(String password) {
        return String.valueOf(String.valueOf(password.hashCode()).hashCode());
    }
}
