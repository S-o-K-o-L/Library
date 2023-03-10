package database;

import java.sql.*;

public class Main {

    //вынести URL в properties
    //сделать инт лонгом -  в бд Бигнитом
    //entity и др сущности
    //genre сделать enum
    //поставить default значения для NOT NULL

    private static final String URL = "jdbc:postgresql://localhost:5432/Library";
    private static final String user = "postgres";
    private static final String password = "1111";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, user, password);
             Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("select * from genre");
            Genre genre = new Genre();
            while (resultSet.next()) {
                genre.setIdGenre(resultSet.getInt(1));
                genre.setNameGenre(resultSet.getString(2));
            }
            System.out.println(genre.getIdGenre() + " - " + genre.getNameGenre());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
