package com.sonnesen.book_reading_tracker.infrastructure.adapters.inbound.rest;

import java.time.Instant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.sonnesen.book_reading_tracker.application.domain.exception.DomainEntityNotFoundException;
import com.sonnesen.book_reading_tracker.application.domain.exception.EmailAlreadyInUseException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DomainEntityNotFoundException.class)
    public ProblemDetail handleDomainEntityNotFoundException(final DomainEntityNotFoundException e) {
        final var problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);
        problemDetail.setTitle("Not Found");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ProblemDetail handleEmailAlreadyInUseException(final EmailAlreadyInUseException e) {
        final var problemDetail = ProblemDetail.forStatus(HttpStatus.CONFLICT);
        problemDetail.setTitle("Conflict");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(final IllegalArgumentException e) {
        final var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValidException(final MethodArgumentNotValidException e) {
        final var invalidFields = e.getFieldErrors().stream()
                .map(field -> new InvalidField(field.getField(), field.getDefaultMessage()))
                .toList();

        final var problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Bad Request");
        problemDetail.setDetail("One or more validation errors occurred");
        problemDetail.setProperty("timestamp", Instant.now());
        problemDetail.setProperty("invalid-fields", invalidFields);
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(final Exception e) {
        final var problemDetail = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        problemDetail.setTitle("Internal Server Error");
        problemDetail.setDetail(e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    private record InvalidField(String name, String message) {
    }
}
