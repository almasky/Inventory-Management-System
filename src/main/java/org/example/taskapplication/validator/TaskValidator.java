package org.example.taskapplication.validator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TaskValidator {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static boolean isValidDescription(String description) {
        return description != null && !description.trim().isEmpty();
    }

    public static boolean isValidStatus(String status) {
        return status != null && !status.trim().isEmpty();
    }
public static boolean isValidDueDate(String dueDate) {
    if (dueDate == null || dueDate.trim().isEmpty()) {
        return false;
    }
    try {
        DATE_FORMATTER.parse(dueDate);
        return true;
    } catch (DateTimeParseException e) {
        return false;
    }
}

    public static LocalDate parseDueDate(String dueDate) {
        return LocalDate.parse(dueDate, DATE_FORMATTER);
    }
}
