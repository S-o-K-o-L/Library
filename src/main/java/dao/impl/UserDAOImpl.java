package dao.impl;

import dao.UserDAO;
import entities.User;
import exceptions.AddEntityException;
import exceptions.FindEntityException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;

public class UserDAOImpl implements UserDAO {
    private final Connection connection = ConnectionFactory.getConnection();
    private static final Logger logger = LogManager.getLogger(UserDAOImpl.class);

    @Override
    public void add(User user) throws AddEntityException {
        String sql = "INSERT INTO users (first_name, last_name, login, password, telephone_number)" +
                "VALUES (?,?,?,?,?)";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getTelephoneNumber());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            logger.error("Ошибка добавления пользователя:\n" + e.getMessage());
            throw new AddEntityException(e.getMessage());
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        User user = null;
        String sql = "SELECT id, first_name, last_name, login, password, telephone_number" +
                " FROM users WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска пользователя:\n" + e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByLogin(String login) throws FindEntityException {
        User user = null;
        String sql = "SELECT id, first_name, last_name, login, password, telephone_number" +
                " FROM users WHERE login = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = createUserFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска пользователя по логину:\n" + e.getMessage());
            throw new FindEntityException(e.getMessage());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT id, first_name, last_name, login, password, telephone_number" +
                " FROM users";
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                usersList.add(createUserFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска всех пользователей:\n" + e.getMessage());
        }
        return usersList;
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET first_name = (?), last_name = (?), login = (?)," +
                "password = (?), telephone_number = (?) WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getTelephoneNumber());
            preparedStatement.setLong(6, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка обновление пользователя:\n" + e.getMessage());
        }
    }

    @Override
    public void remove(User user) {
        String sql = "DELETE FROM users WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка удаления пользователя:\n" + e.getMessage());
        }
    }

    private User createUserFromResultSet(ResultSet resultSet) throws SQLException {
        Long userId = resultSet.getLong(1);
        String firstName = resultSet.getString(2);
        String lastName = resultSet.getString(3);
        String login = resultSet.getString(4);
        String password = resultSet.getString(5);
        String telephoneNumber = resultSet.getString(6);
        return new User(userId, firstName, lastName, telephoneNumber, login, password);
    }
}