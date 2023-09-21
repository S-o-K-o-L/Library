package dao;

import dao.impl.BookDAOImpl;
import dao.impl.UserDAOImpl;
import dao.impl.UserBookDAOImpl;
import entities.UserBook;
import exceptions.AddEntityException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static data.Data.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserBookDAOTest {
    private final UserBookDAO usersBooksDAO = new UserBookDAOImpl();
    private final UserDAO userDAO = new UserDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();

    @BeforeEach
    public void prepare() throws AddEntityException {
        userDAO.add(user);
        bookDAO.add(book);
    }

    @AfterEach
    public void ending() {
        userDAO.remove(user);
        bookDAO.remove(book);
    }

    @Test
    public void testAddAndDelete() {
        UserBook usersBooks = new UserBook(null, book.getId(), user.getId());
        usersBooksDAO.add(usersBooks);
        assertEquals(usersBooks.toString(),
                usersBooksDAO.findByIdBookAndIdUser(book.getId(), user.getId()).orElse(null).toString());
        usersBooksDAO.remove(usersBooksDAO
                .findByIdBookAndIdUser(book.getId(), user.getId()).orElse(usersBooks));
        assertNull(usersBooksDAO.findByIdBookAndIdUser(book.getId(), user.getId()).orElse(null));
    }

    @Test
    public void testFindAllForUser() {
        UserBook usersBooks = new UserBook(null, book.getId(), user.getId());
        List<UserBook> usersBooksList = usersBooksDAO.findBooksForUser(user);
        usersBooksDAO.add(usersBooks);
        assertEquals(usersBooksList.size() + 1,
                usersBooksDAO.findBooksForUser(user).size());
        usersBooksDAO.remove(usersBooksDAO
                .findByIdBookAndIdUser(book.getId(), user.getId()).orElse(null));
    }

    @Test
    public void testFindAll() {
        UserBook usersBooks = new UserBook(null, book.getId(), user.getId());
        List<UserBook> usersBooksList = usersBooksDAO.findAll();
        usersBooksDAO.add(usersBooks);
        assertEquals(usersBooksList.size() + 1,
                usersBooksDAO.findAll().size());
        usersBooksDAO.remove(usersBooksDAO
                .findByIdBookAndIdUser(book.getId(), user.getId()).orElse(null));
    }
}
