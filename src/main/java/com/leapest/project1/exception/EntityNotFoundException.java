package com.leapest.project1.exception;

/**
 * Exception for entity not found
 */
public class EntityNotFoundException extends Exception {
    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
