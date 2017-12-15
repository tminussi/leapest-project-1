package com.leapest.project1.exception;

/**
 * Exception for invalid id format
 */
public class InvalidIdException extends Exception {
    public InvalidIdException() {
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
