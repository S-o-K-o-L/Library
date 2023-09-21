package service.impl;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import entities.Book;
import service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO = new BookDAOImpl();

    @Override
    public void add(Book book) {
        bookDAO.add(book);
    }

    @Override
    public Book findById(Long id) {
        return bookDAO.findById(id).orElse(null);
    }

    @Override
    public Book findByName(String name) {
        return bookDAO.findByName(name).orElse(null);
    }

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void remove(Book book) {
        bookDAO.remove(book);
    }
}
