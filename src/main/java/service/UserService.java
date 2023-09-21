package service;

import entities.User;
import exceptions.AddEntityException;
import exceptions.FindEntityException;

import java.util.List;

public interface UserService {
    boolean create(User user) throws AddEntityException;
    User findById(Long id);
    User findByLogin(String login, String password) throws FindEntityException;
    List<User> findAll();
    void update(User user);
    void remove(User user);
}
