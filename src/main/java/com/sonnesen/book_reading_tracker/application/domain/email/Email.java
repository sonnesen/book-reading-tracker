package com.sonnesen.book_reading_tracker.application.domain.email;

public class Email {

    private final String value;

    private Email(final String value) {
        this.value = value;
        validate();
    }

    public static Email from(final String value) {
        return new Email(value);
    }

    private void validate() {
        new EmailValidator(this).validate();
    }

    public String getValue() {
        return value;
    }

}
