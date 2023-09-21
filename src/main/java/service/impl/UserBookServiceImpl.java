package service.impl;

import dao.UserBookDAO;
import dao.impl.UserBookDAOImpl;
import entities.User;
import entities.UserBook;
import service.UserBookService;

import java.util.List;

public class UserBookServiceImpl implements UserBookService {
    private final UserBookDAO userBookDAO = new UserBookDAOImpl();

    @Override
    public void add(UserBook userBook) {
        userBookDAO.add(userBook);
    }

    @Override
    public UserBook findByIdBookAndIdUser(Long idBook, Long idUser) {
        return userBookDAO.findByIdBookAndIdUser(idBook, idUser).orElse(null);
    }

    @Override
    public List<UserBook> findAll() {
        return userBookDAO.findAll();
    }

    @Override
    public List<UserBook> findBooksForUser(User user) {
        return userBookDAO.findBooksForUser(user);
    }

    @Override
    public void remove(UserBook userBook) {
        userBookDAO.remove(userBook);
    }
}
