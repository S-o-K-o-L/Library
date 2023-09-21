package dao.impl;

import dao.BookDAO;
import dao.ChapterDAO;
import entities.Book;
import entities.enums.Genre;
import org.apache.log4j.Logger;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class BookDAOImpl implements BookDAO {
    //TODO
    // добавить generated key возвращение
    // доработать поиск по айди
    // доработать удаление по айди
    private final Connection connection = ConnectionFactory.getConnection();
    private static final Logger logger = Logger.getLogger(BookDAOImpl.class);

    @Override
    public void add(Book book) {
        String sql = "INSERT INTO book (name, description, genres) " +
                "VALUES (?,?,?)";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Array genresArray = connection.createArrayOf("TEXT", book.getGenres().toArray());
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setArray(3, genresArray);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                book.setId(resultSet.getLong("id"));
                ChapterDAO chapterDAO = new ChapterDAOImpl();
                book.getBookChapters()
                        .forEach(x -> {
                            x.setIdBook(book.getId());
                            chapterDAO.add(x, book.getId());
                        });
            }
        } catch (SQLException e) {
            logger.error("Ошибка добавления книги:\n" + e.getMessage());
        }
    }

    @Override
    public Optional<Book> findById(Long id) {
        Book book = null;
        String sql = "SELECT id, name, description, genres FROM Book WHERE id=(?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            ChapterDAO chapterDAO = new ChapterDAOImpl();
            if (resultSet.next()) {
                book = new Book(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        getSetGenres(resultSet),
                        chapterDAO.findAllForIdBook(resultSet.getLong("id")));
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска книги:\n" + e.getMessage());
        }
        return Optional.ofNullable(book);
    }

    @Override
    public Optional<Book> findByName(String name) {
        Book book = null;
        String sql = "SELECT id, name, description, genres FROM Book WHERE name=(?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            ChapterDAO chapterDAO = new ChapterDAOImpl();
            if (resultSet.next()) {
                book = new Book(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        getSetGenres(resultSet),
                        chapterDAO.findAllForIdBook(resultSet.getLong("id")));
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска книги:\n" + e.getMessage());
        }
        return Optional.ofNullable(book);
    }

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT id, name, description, genres FROM Book";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            ChapterDAO chapterDAO = new ChapterDAOImpl();
            while (resultSet.next()) {
                books.add(new Book(resultSet.getLong("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"),
                        getSetGenres(resultSet),
                        chapterDAO.findAllForIdBook(resultSet.getLong("id")))
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска всех книг:\n" + e.getMessage());
        }
        return books;
    }

    @Override
    public void update(Book book) {
        String sql = "UPDATE book SET name=(?), description=(?),genres=(?)" +
                "WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            Array arrayGenres = connection.createArrayOf("TEXT", book.getGenres().toArray());
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setArray(3, arrayGenres);
            preparedStatement.setLong(4,book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка обновления книги:\n" + e.getMessage());
        }
    }

    @Override
    public void remove(Book book) {
        String sql = "DELETE FROM book WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, book.getId());
            preparedStatement.executeUpdate();
            ChapterDAO chapterDAO = new ChapterDAOImpl();
            chapterDAO.findAllForIdBook(book.getId()).forEach(chapterDAO::remove);
        } catch (SQLException e) {
            logger.error("Ошибка удаления книги:\n" + e.getMessage());
        }
    }

    private Set<Genre> getSetGenres(ResultSet resultSet) throws SQLException {
        Array genres = resultSet.getArray("genres");
        String[] genresStr = (String[]) genres.getArray();
        return Arrays.stream(genresStr)
                .map(x -> Genre.valueOf(x.toUpperCase()))
                .collect(Collectors.toCollection(TreeSet::new));
    }
}
