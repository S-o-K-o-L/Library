package service;

import entities.Book;

import java.util.List;

public interface BookService {
    void add(Book book);
    Book findById(Long id);
    Book findByName(String name);
    List<Book> findAll();
    void update(Book book);
    void remove(Book book);
}
