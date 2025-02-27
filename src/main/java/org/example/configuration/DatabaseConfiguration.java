package org.example.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfiguration {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/to_do_app";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            } catch (SQLException e) {
                System.err.println("Failed to establish a database connection: " + e.getMessage());
                throw new DatabaseActionException("Unable to connect to the database", e);
            }
        }
        return connection;
    }
}

//    public static void closeConnection() {
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                System.err.println("Failed to close the database connection: " + e.getMessage());
//            }
//        }
//    }

//    public static void createTables() {
//        String userTable = "CREATE TABLE IF NOT EXISTS User ("
//                + "id INT AUTO_INCREMENT PRIMARY KEY,"
//                + "username VARCHAR(255) NOT NULL,"
//                + "password VARCHAR(255) NOT NULL,"
//                + "email VARCHAR(255) NOT NULL"
//                + ");";
//
//        String taskTable = "CREATE TABLE IF NOT EXISTS Task ("
//                + "id INT AUTO_INCREMENT PRIMARY KEY,"
//                + "user_id INT NOT NULL,"
//                + "description VARCHAR(255) NOT NULL,"
//                + "status VARCHAR(255) NOT NULL,"
//                + "FOREIGN KEY (user_id) REFERENCES User(id)"
//                + ");";
//
//        try (Statement statement = getConnection().createStatement()) {
//            statement.execute(userTable);
//            statement.execute(taskTable);
//            System.out.println("Tables created successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void dropTables() {
//        String dropTaskTable = "DROP TABLE IF EXISTS Task;";
//        String dropUserTable = "DROP TABLE IF EXISTS User;";
//
//        try (Statement statement = getConnection().createStatement()) {
//            statement.execute(dropTaskTable);
//            statement.execute(dropUserTable);
//            System.out.println("Tables dropped successfully.");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }



