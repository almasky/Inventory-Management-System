package org.example.taskapplication.input;

import org.example.taskapplication.doa.TaskDAO;
import org.example.taskapplication.doa.TaskDAOImpl;
import org.example.taskapplication.doa.UserDAO;
import org.example.taskapplication.doa.UserDAOImpl;
import org.example.taskapplication.model.Task;
import org.example.taskapplication.model.User;
import org.example.taskapplication.validator.TaskValidator;
import org.example.taskapplication.validator.UserValidator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class InputHandler {
    private UserDAO userDAO;
    private TaskDAO taskDAO;
    private Scanner scanner;

    public InputHandler() {
        this.userDAO = new UserDAOImpl();
        this.taskDAO = new TaskDAOImpl();
        this.scanner = new Scanner(System.in);
    }

    public void handleInput() {
        while (true) {
            System.out.println("\nChoose an action: [1] User Actions, [2] Task Actions, [3] Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    handleUserInput();
                    break;
                case 2:
                    handleTaskInput();
                    break;
                case 3:
                    exitApplication();
                    break;
                default:
                    System.out.println("Invalid choice. Please choose a valid option.");
            }
        }
    }

    private void handleUserInput() {
        System.out.println("\nUser Actions: [1] Create User, [2] Get User by ID, [3] Get All Users, [4] Update User, [5] Delete User, [6] Back");
        System.out.println("Enter your choice: ");
        int action = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (action) {
            case 1:
                createUser();
                break;
            case 2:
                getUserById();
                break;
            case 3:
                getAllUsers();
                break;
            case 4:
                updateUser();
                break;
            case 5:
                deleteUser();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid action. Please choose a valid action.");
        }
    }

    private void handleTaskInput() {
        System.out.println("\nTask Actions: [1] Create Task, [2] Get Task by ID, [3] Get All Tasks, [4] Update Task, [5] Delete Task, [6] Back");
        System.out.println("Enter your choice: ");
        int action = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        switch (action) {
            case 1:
                createTask();
                break;
            case 2:
                getTaskById();
                break;
            case 3:
                getAllTasks();
                break;
            case 4:
                updateTask();
                break;
            case 5:
                deleteTask();
                break;
            case 6:
                return;
            default:
                System.out.println("Invalid action. Please choose a valid action.");
        }
    }


    private void createUser() {
        User newUser = new User();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        if (!UserValidator.isValidUsername(username)) {
            System.out.println("Invalid username. Please try again.");
            return;
        }
        newUser.setUsername(username);
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (!UserValidator.isValidPassword(password)) {
            System.out.println("Invalid password. Please try again.");
            return;
        }
        newUser.setPassword(password);
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (!UserValidator.isValidEmail(email)) {
            System.out.println("Invalid email. Please try again.");
            return;
        }
        newUser.setEmail(email);
        userDAO.createUser(newUser);
        System.out.println("User created successfully: " + newUser);
    }

    private void getUserById() {
        System.out.print("Enter user ID: ");
        int userId = scanner.nextInt();
        User user = userDAO.getUserById(userId);
        if (user != null) {
            System.out.println("User found: " + user);
        } else {
            System.out.println("User not found.");
        }
    }

    private void getAllUsers() {
        System.out.println("All users:");
        userDAO.getAllUsers().forEach(System.out::println);
    }

    private void updateUser() {
        System.out.print("Enter user ID to update: ");
        int updateUserById = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        User updateUser = userDAO.getUserById(updateUserById);
        if (updateUser != null) {
            System.out.print("Enter new username: ");
            String newUsername = scanner.nextLine();
            if (!UserValidator.isValidUsername(newUsername)) {
                System.out.println("Invalid username. Please try again.");
                return;
            } else {
                updateUser.setUsername(newUsername);
            }
            System.out.print("Enter new password: ");
            String newPassword = scanner.nextLine();
            if (!UserValidator.isValidPassword(newPassword)) {
                System.out.println("Invalid password. Please try again.");
                return;
            } else {
                updateUser.setPassword(newPassword);
            }
            System.out.print("Enter new email: ");
            String newEmail = scanner.nextLine();
            if (!UserValidator.isValidEmail(newEmail)) {
                System.out.println("Invalid email. Please try again.");
                return;
            } else {
                updateUser.setEmail(newEmail);
            }
            userDAO.updateUser(updateUser);
            System.out.println("User updated successfully: " + updateUser);
        } else {
            System.out.println("User not found.");
        }
    }

    private void deleteUser() {
        System.out.print("Enter user ID to delete: ");
        int deleteUserId = scanner.nextInt();
        User deleteUser = userDAO.getUserById(deleteUserId);
        if (deleteUser != null) {
            userDAO.deleteUser(deleteUser);
            System.out.println("User deleted successfully: " + deleteUser);
        } else {
            System.out.println("User not found.");
        }
    }

    private void createTask() {
        Task newTask = new Task();
        System.out.println("Enter user ID to assign task to: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        User user = userDAO.getUserById(userId);
        if (user != null) {
            newTask.setUser(user);
            System.out.println("Enter task description:");
            String description = scanner.nextLine();
            if (!TaskValidator.isValidDescription(description)) {
                System.out.println("Description cannot be empty. Please try again.");
                return;
            } else {
                newTask.setDescription(description);
            }
            System.out.println("Enter task status:");
            String status = scanner.nextLine();
            if (!TaskValidator.isValidStatus(status)) {
                System.out.println("Status cannot be empty. Please try again.");
                return;
            } else {
                newTask.setStatus(status);
            }
            System.out.println("Enter task due date in the format (DD/MM/YYYY): ");
            String dueDate = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //correct date format
            try {
                LocalDate DueDate = LocalDate.parse(dueDate, formatter);
                //parse with custome formatter
                newTask.setDueDate(DueDate);
                taskDAO.createTask(newTask);
                System.out.println("Task created successfully: " + newTask);
                return;
            }catch (DateTimeParseException e){
                System.out.println("Invalid date format. Please enter the date in DD/MM/YYYY format.");
            }

        } else {
            System.out.println("User not found.");
        }
    }

    private void getTaskById() {
        System.out.print("Enter task ID: ");
        int taskId = scanner.nextInt();
        Task task = taskDAO.getTaskById(taskId);
        if (task != null) {
            System.out.println("Task found: " + task);
        } else {
            System.out.println("Task not found.");
        }
    }

    private void getAllTasks() {
        System.out.println("All tasks:");
        taskDAO.getAllTasks().forEach(System.out::println);
    }

    private void updateTask() {
        System.out.print("Enter task ID to update: ");
        int updateTaskById = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Task updateTask = taskDAO.getTaskById(updateTaskById);
        if (updateTask != null) {
            System.out.print("Enter new description: ");
            String newDescription = scanner.nextLine();
            if (!TaskValidator.isValidDescription(newDescription)) {
                System.out.println("Description cannot be empty. Please try again.");
                return;
            } else {
                updateTask.setDescription(newDescription);
            }
            System.out.print("Enter new status: ");
            String newStatus = scanner.nextLine();
            if (!TaskValidator.isValidStatus(newStatus)) {
                System.out.println("Status cannot be empty. Please try again.");
                return;
            } else {
                updateTask.setStatus(newStatus);
            }
            System.out.print("Enter new due date (DD/MM/YYYY): ");
            String dueDate = scanner.nextLine();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            //correct date format
            try {
                LocalDate DueDate = LocalDate.parse(dueDate, formatter);
                //parse with custom formatter
                updateTask.setDueDate(DueDate);
                taskDAO.updateTask(updateTask);
                System.out.println("Task updated successfully: " + updateTask);
            }catch (DateTimeParseException e){
                System.out.println("Invalid date format. Please enter the date in DD/MM/YYYY format.");
            }
        } else{
            System.out.println("Task not found.");
            }
        }

    private void deleteTask() {
        System.out.print("Enter task ID to delete: ");
        int deleteTaskId = scanner.nextInt();
        Task deleteTask = taskDAO.getTaskById(deleteTaskId);
        if (deleteTask != null) {
            taskDAO.deleteTask(deleteTask);
            System.out.println("Task deleted successfully: " + deleteTask);
        } else {
            System.out.println("Task not found.");
        }
    }

    private void exitApplication() {
        System.out.println("Exiting...");
        scanner.close();
        System.exit(0);
    }
}