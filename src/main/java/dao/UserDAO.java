package dao;

import entities.User;
import exceptions.AddEntityException;
import exceptions.FindEntityException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    void add(User user) throws AddEntityException;
    Optional<User> findById(Long id);
    Optional<User> findByLogin(String login) throws FindEntityException;
    List<User> findAll();
    void update(User user);
    void remove(User user);
}
