package dao.impl;

import dao.ChapterDAO;
import entities.Chapter;
import org.apache.log4j.Logger;
import utils.ConnectionFactory;

import java.sql.*;
import java.util.*;

public class ChapterDAOImpl implements ChapterDAO {
    private final Connection connection = ConnectionFactory.getConnection();
    private static final Logger logger = Logger.getLogger(ChapterDAOImpl.class);

    @Override
    public void add(Chapter chapter, Long idBook) {
        String sql = "INSERT INTO chapter (content, number, id_book)" +
                "VALUES (?,?,?)";
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, chapter.getContent());
            preparedStatement.setInt(2, chapter.getNumber());
            preparedStatement.setLong(3, idBook);
            chapter.setIdBook(idBook);
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                chapter.setId(resultSet.getLong("id"));
            }
        } catch (SQLException e) {
            logger.error("Ошибка добавления главы:\n" + e.getMessage());
        }
    }

    @Override
    public Optional<Chapter> findById(Long id) {
        Chapter chapter = null;
        String sql = "SELECT id, content, number, id_book FROM chapter WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chapter = new Chapter(resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getInt("number"),
                        resultSet.getLong("id_book")
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска главы по id:\n" + e.getMessage());
        }
        return Optional.ofNullable(chapter);
    }

    @Override
    public Optional<Chapter> findByNumber(Integer number, Long idBook) {
        Chapter chapter = null;
        String sql = "SELECT id, content, number, id_book FROM chapter " +
                "WHERE number = (?) AND id_book=(?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, number);
            preparedStatement.setLong(2, idBook);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                chapter = new Chapter(resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getInt("number"),
                        resultSet.getLong("id_book")
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска главы по number:\n" + e.getMessage());
        }
        return Optional.ofNullable(chapter);
    }

    @Override
    public Set<Chapter> findAllForIdBook(Long idBook) {
        Set<Chapter> chapters = new HashSet<>();
        String sql = "SELECT id, content, number, id_book FROM chapter WHERE id_book = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, idBook);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                chapters.add(new Chapter(resultSet.getLong("id"),
                        resultSet.getString("content"),
                        resultSet.getInt("number"),
                        resultSet.getLong("id_book"))
                );
            }
        } catch (SQLException e) {
            logger.error("Ошибка поиска глав у книги:\n" + e.getMessage());
        }
        return chapters;
    }

    @Override
    public void update(Chapter chapter) {
        String sql = "UPDATE chapter SET content=(?), number=(?), id_book=(?)" +
                "WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, chapter.getContent());
            preparedStatement.setInt(2, chapter.getNumber());
            preparedStatement.setLong(3, chapter.getIdBook());
            preparedStatement.setLong(4, chapter.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка обновления главы:\n" + e.getMessage());
        }
    }

    @Override
    public void remove(Chapter chapter) {
        String sql = "DELETE FROM chapter WHERE id = (?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1,chapter.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Ошибка удаления главы:\n" + e.getMessage());
        }
    }
}
