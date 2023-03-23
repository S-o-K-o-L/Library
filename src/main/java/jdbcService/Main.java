package jdbcService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Main {
    private static final String PATH = "src\\main\\resources\\db.properties";
    public static void main(String[] args) {
        Properties properties =
                getPropertiesFromFile();

        String URL = properties.getProperty("jdbc.URL");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        try (Connection connection = DriverManager.getConnection(URL, username, password);
             Statement statement = connection.createStatement()
        ) { PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chapter (id, content, number, id_book) VALUES (?, ?, ?,?)");
            preparedStatement.setLong(1,50);
            preparedStatement.setString(2,"QWEQDSAFDFW");
            preparedStatement.setInt(3,5);
            preparedStatement.setLong(4,1);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Properties getPropertiesFromFile() {
        Properties properties = new Properties();
        try (InputStream fileProperties = new FileInputStream(PATH)) {
            properties.load(fileProperties);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
