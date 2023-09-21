package dao.impl;

import dao.UserBookDAO;
import entities.User;
import entities.UserBook;
import org.apache.log4j.Logger;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBookDAOImpl implements UserBookDAO {
    private final Connection connection = ConnectionFactory.getConnection();
    private static final Logger logger = Logger.getLogger(UserBookDAOImpl.class);


    @Override
    public void add(UserBook user) {
        String sql = "INSERT INTO users_books (id_users, id_books) " +
                "VALUES (?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, user.getIdUser());
            preparedStatement.setLong(2, user.getIdBook());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка добавления книги у пользователя:\n" + e.getMessage());
        }
    }

    @Override
    public Optional<UserBook> findByIdBookAndIdUser(Long idBook, Long idUser) {
        UserBook usersBooks = null;
        String sql = "SELECT id, id_users, id_books FROM users_books " +
                "WHERE id_books = (?) AND id_users =(?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, idBook);
            statement.setLong(2, idUser);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                usersBooks =
                        new UserBook(resultSet.getLong("id"),
                                resultSet.getLong("id_books"),
                                resultSet.getLong("id_users")
                        );
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска книги у пользователя:\n" + e.getMessage());
        }
        return Optional.ofNullable(usersBooks);
    }

    @Override
    public List<UserBook> findAll() {
        List<UserBook> usersBooksList = new ArrayList<>();
        String sql = "SELECT id, id_users, id_books FROM users_books";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                usersBooksList.add(
                        new UserBook(resultSet.getLong("id"),
                                resultSet.getLong("id_books"),
                                resultSet.getLong("id_users"))
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска всех книг у пользователя:\n" + e.getMessage());
        }
        return usersBooksList;
    }

    @Override
    public List<UserBook> findBooksForUser(User user) {
        List<UserBook> usersBooksList = new ArrayList<>();
        String sql = "SELECT id, id_users, id_books FROM users_books WHERE id_users=(?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, user.getId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                usersBooksList.add(
                        new UserBook(resultSet.getLong("id"),
                                resultSet.getLong("id_books"),
                                resultSet.getLong("id_users"))
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска всех книг у пользователя:\n" + e.getMessage());
        }
        return usersBooksList;
    }

    @Override
    public void remove(UserBook usersBooks) {
        String sql = "DELETE FROM users_books " +
                "WHERE id_books = (?) AND id_users = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, usersBooks.getIdBook());
            preparedStatement.setLong(2, usersBooks.getIdUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка удаления книги у пользователя:\n" + e.getMessage());
        }
    }
}
