package service;

import entities.User;
import entities.UserBook;

import java.util.List;

public interface UserBookService {
    void add(UserBook usersBooks);
    UserBook findByIdBookAndIdUser(Long idBook, Long idUser);
    List<UserBook> findAll();
    List<UserBook> findBooksForUser(User user);
    void remove(UserBook usersBooks);
}
