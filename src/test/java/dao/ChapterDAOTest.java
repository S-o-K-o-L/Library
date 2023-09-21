package dao;

import dao.impl.BookDAOImpl;
import dao.impl.ChapterDAOImpl;
import entities.Chapter;
import entities.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static data.Data.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChapterDAOTest {
    private final ChapterDAO chapterDAO = new ChapterDAOImpl();
    private final BookDAO bookDAO = new BookDAOImpl();

    @BeforeEach
    public void prepare() {
        bookDAO.add(book);
    }

    @AfterEach
    public void ending() {
        bookDAO.remove(book);
    }

    @Test
    public void testAddAndDeleteChapter() {
        chapterDAO.add(chapterSuper, book.getId());
        assertEquals(chapterSuper.toString(),
                chapterDAO.findByNumber(chapterSuper.getNumber(), book.getId()).orElse(null).toString());
        chapterDAO.remove(chapterDAO
                .findByNumber(chapterSuper.getNumber(), book.getId()).orElse(chapterSuper));
        assertNull(chapterDAO.findByNumber(chapterSuper.getNumber(), book.getId()).orElse(null));
    }

    @Test
    public void testFindAllForIdBook() {
        Set<Chapter> chapters = chapterDAO.findAllForIdBook(book.getId());
        chapterDAO.add(chapterSuper, book.getId());
        assertEquals(chapters.size() + 1,
                chapterDAO.findAllForIdBook(book.getId()).size());
        chapterDAO.remove(chapterDAO
                .findByNumber(chapterSuper.getNumber(), book.getId()).orElse(null));
    }

    @Test
    public void testUpdate() {
        chapterDAO.add(chapterSuper, book.getId());
        chapterSuper.setContent("123");
        chapterDAO.update(chapterSuper);
        assertEquals(chapterSuper.toString(),
                chapterDAO.findByNumber(chapterSuper.getNumber(), book.getId()).orElse(null).toString());
        chapterDAO.remove(chapterDAO
                .findByNumber(chapterSuper.getNumber(), book.getId()).orElse(null));
    }
}
