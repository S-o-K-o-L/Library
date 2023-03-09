package database;

import java.sql.*;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost:5432/Library";
    private static final String user = "postgres";
    private static final String password = "1";

    public static void main(String[] args) {
        try(Connection connection = DriverManager.getConnection(URL,user,password);
            Statement statement = connection.createStatement()
        ) {
            ResultSet resultSet = statement.executeQuery("select * from users");
            resultSet.next();
            System.out.println(resultSet.getString(4));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
