package com.sonnesen.book_reading_tracker.application.domain.email;

import java.util.regex.Pattern;

public class EmailValidator {

    private static final int MAX_LENGTH = 255;
    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    private final Email email;

    public EmailValidator(Email email) {
        this.email = email;
    }

    public void validate() {
        checkEmailConstraints();
    }

    private void checkEmailConstraints() {
        final var emailValue = email.getValue();

        if (emailValue == null || emailValue.isBlank()) {
            throw new IllegalArgumentException("Email cannot be null or blank");
        }

        if (!EMAIL_PATTERN.matcher(emailValue).matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }

        if (emailValue.length() > MAX_LENGTH) {
            throw new IllegalArgumentException("Email cannot be longer than " + MAX_LENGTH + " characters");
        }
    }
}
