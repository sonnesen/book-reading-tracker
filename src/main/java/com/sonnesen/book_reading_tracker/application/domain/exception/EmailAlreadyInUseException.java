package com.sonnesen.book_reading_tracker.application.domain.exception;

public class EmailAlreadyInUseException extends RuntimeException {

    public EmailAlreadyInUseException(final String email) {
        super(String.format("Email %s is already in use", email));
    }
}
