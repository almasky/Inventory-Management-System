package org.example.configuration;
public class DatabaseActionException extends RuntimeException {
    public DatabaseActionException(String message, Throwable cause) {

        super(message, cause);
    }
}