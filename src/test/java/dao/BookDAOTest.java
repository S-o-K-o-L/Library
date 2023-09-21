package dao;

import dao.impl.BookDAOImpl;
import entities.Book;
import org.junit.jupiter.api.Test;

import java.util.List;

import static data.Data.book;
import static org.junit.jupiter.api.Assertions.*;

public class BookDAOTest {
    private final BookDAO bookDAO = new BookDAOImpl();

    @Test
    public void testAddAndDelete() {
        bookDAO.add(book);
        assertEquals(book.toString(), bookDAO.findByName(book.getName()).orElse(null).toString());
        bookDAO.remove(bookDAO.findByName(book.getName()).orElse(book));
        assertNull(bookDAO.findByName(book.getName()).orElse(null));
    }

    @Test
    public void testFindAll() {
        List<Book> bookList = bookDAO.findAll();
        bookDAO.add(book);
        assertEquals(bookList.size() + 1, bookDAO.findAll().size());
        bookDAO.remove(book);
    }

    @Test
    public void testUpdate() {
        bookDAO.add(book);
        book.setDescription("Вау");
        bookDAO.update(book);
        assertEquals(book.toString(), bookDAO
                .findByName(book.getName()).orElse(null).toString());
        bookDAO.remove(book);
    }
}
