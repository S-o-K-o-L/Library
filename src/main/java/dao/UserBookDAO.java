package dao;

import entities.User;
import entities.UserBook;

import java.util.List;
import java.util.Optional;

public interface UserBookDAO {
    void add(UserBook usersBooks);
    Optional<UserBook> findByIdBookAndIdUser(Long idBook, Long idUser);
    List<UserBook> findAll();
    List<UserBook> findBooksForUser(User user);
    void remove(UserBook usersBooks);
}
