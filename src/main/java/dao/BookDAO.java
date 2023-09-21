package dao;

import entities.Book;
import entities.Chapter;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface BookDAO {
    void add(Book book);

    Optional<Book> findById(Long id);

    Optional<Book> findByName(String name);

    List<Book> findAll();

    void update(Book book);

    void remove(Book book);
}
