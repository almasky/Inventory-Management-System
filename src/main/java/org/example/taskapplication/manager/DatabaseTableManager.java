package org.example.taskapplication.manager;

import org.example.configuration.DatabaseConfiguration;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseTableManager {

    public static void createTables() {
        String userTable = "CREATE TABLE IF NOT EXISTS User ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "username VARCHAR(255) NOT NULL,"
                + "password VARCHAR(255) NOT NULL,"
                + "email VARCHAR(255) NOT NULL"
                + ");";
        String taskTable = "CREATE TABLE IF NOT EXISTS Task ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "user_id INT NOT NULL,"
                + "description VARCHAR(255) NOT NULL,"
                + "status VARCHAR(255) NOT NULL,"
                + "FOREIGN KEY (user_id) REFERENCES User(id)"
                + ");";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(userTable);
            statement.execute(taskTable);
            System.out.println("Tables created successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void dropTables() {
        String dropTaskTable = "DROP TABLE IF EXISTS Task;";
        String dropUserTable = "DROP TABLE IF EXISTS User;";

        try (Connection connection = DatabaseConfiguration.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(dropTaskTable);
            statement.execute(dropUserTable);
            System.out.println("Tables dropped successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
