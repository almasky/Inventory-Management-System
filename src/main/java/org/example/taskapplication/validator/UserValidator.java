package org.example.taskapplication.validator;

public class UserValidator {
    public static boolean isValidUsername(String username) {
        return username != null && !username.trim().isEmpty();
    }
    public static boolean isValidPassword(String password) {
        return password != null && !password.trim().isEmpty();
    }
    public static boolean isValidEmail(String email) {
        return email != null && !email.contains("@");
    }
}
