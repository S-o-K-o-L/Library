package dao;

import dao.impl.UserDAOImpl;
import entities.User;
import exceptions.AddEntityException;
import exceptions.FindEntityException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static data.Data.user;
import static org.junit.jupiter.api.Assertions.*;

public class UserDAOTest {
    private final UserDAO userDAO = new UserDAOImpl();

    @Test
    public void testAddAndDeleteUser() throws AddEntityException, FindEntityException {
        userDAO.add(user);
        assertEquals(user.toString(),
                userDAO.findByLogin(user.getLogin()).orElse(null).toString());
        userDAO.remove(userDAO.findByLogin(user.getLogin()).orElse(user));
        assertNull(userDAO.findByLogin(user.getLogin()).orElse(null));
    }

    @Test
    public void testFindAll() throws AddEntityException, FindEntityException {
        List<User> users = userDAO.findAll();
        userDAO.add(user);
        assertEquals(users.size() + 1, userDAO.findAll().size());
        userDAO.remove(userDAO.findByLogin(user.getLogin()).orElse(null));
    }

    @Test
    public void testUpdate() throws AddEntityException, FindEntityException {
        userDAO.add(user);
        assertEquals(user.toString(),
                userDAO.findByLogin(user.getLogin()).orElse(null).toString());
        user.setFirstName("Ivan");
        userDAO.update(user);
        assertEquals(user.toString(),
                userDAO.findByLogin(user.getLogin()).orElse(null).toString());
        userDAO.remove(userDAO.findByLogin(user.getLogin()).orElse(null));
    }
}
