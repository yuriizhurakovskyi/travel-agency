package ua.lviv.travelagency.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionManager {
    private static Connection connection = null;

    private ConnectionManager() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = openConnection();
            } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    private static Connection openConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
        Properties properties = new Properties();
        String driver = null;
        String url = null;
        String name = null;
        String password = null;
        String file = ConnectionManager.class.getClassLoader().getResource("db.properties").getFile();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            properties.load(fileInputStream);
            driver = properties.getProperty("db.driver");
            url = properties.getProperty("db.url");
            name = properties.getProperty("db.username");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Class.forName(driver).newInstance();
        return DriverManager.getConnection(url, name, password);
    }

    public static void close() throws SQLException {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}