package data;

import entities.Book;
import entities.Chapter;
import entities.User;
import entities.enums.Genre;

import java.util.Set;

public class Data {
    public static final User user = new User(null,
            "Андрей",
            "Рябчиков",
            "89092121070",
            "google",
            "asdfgjkfl");

    public static final Chapter chapter = new Chapter(null,
            "Глава тестовая! тестовая! тестовая!",
            1,
            null);

    public static final Chapter chapterSuper = new Chapter(null,
            "Глава SUPER тестовая! тестовая! тестовая!",
            2,
            null);

    public static final Book book = new Book(null,
            "Тестовая книга!",
            "ТЕСТЫ ТЕСТЫ ТЕСТЫ",
            Set.of(Genre.ROMANCE, Genre.HORROR, Genre.FANTASY, Genre.SCIENCE_FICTION),
            Set.of(chapter));
}
