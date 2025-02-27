package org.example.taskapplication;

import org.example.taskapplication.input.InputHandler;


public class TaskMain {
    public static void main(String[] args) {

        InputHandler userInputHandler = new InputHandler();
        userInputHandler.handleInput();

        // Create tables
        //DatabaseConfiguration.createTables();

        // Optionally drop tables (for testing purposes)
        // DatabaseConfiguration.dropTables();

//        UserDAO userDAO = new UserDAOImpl();

        // Create a new user
//        User user = new User();
//        user.setUsername("johndoe");
//        user.setPassword("password123");
//        user.setEmail("johndoe@example.com");
//        userDAO.createUser(user);

//        User user2 = new User();
//        user2.setUsername("alma");
//        user2.setPassword("pass456");
//        user2.setEmail("alma.m@gmai.com");
//        userDAO.createUser(user2);

        // Retrieve and print all users
        //userDAO.getAllUsers().forEach(System.out::println);

        // Update user
//        user2.setPassword("newpassword");
//        userDAO.updateUser(user2);

        // Delete user
        //userDAO.deleteUser(user);
    }
}
