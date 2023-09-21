package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class ConnectionFactory {
    public static Connection getConnection() {
        Properties properties =
                getPropertiesFromFile();

        String URL = properties.getProperty("jdbc.URL");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");

        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL,username,password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private static Properties getPropertiesFromFile() {
        Properties properties = new Properties();
        try (InputStream fileProperties = ConnectionFactory.class
                .getClassLoader()
                .getResourceAsStream("db.properties")) {
            properties.load(fileProperties);
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
