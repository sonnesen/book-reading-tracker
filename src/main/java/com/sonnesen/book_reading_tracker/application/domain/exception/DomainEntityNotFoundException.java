package com.sonnesen.book_reading_tracker.application.domain.exception;

public class DomainEntityNotFoundException extends RuntimeException {

    public DomainEntityNotFoundException(final String entityName, final String entityId) {
        super(String.format("%s with ID %s not found", entityName, entityId));
    }
}
