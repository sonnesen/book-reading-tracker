package com.sonnesen.book_reading_tracker.application.domain.member;

import java.util.regex.Pattern;

public class MemberValidator {

    private static final int MAX_NAME_LENGTH = 100;
    private static final int MIN_NAME_LENGTH = 3;
    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z ]+$");

    private final Member member;

    public MemberValidator(Member member) {
        this.member = member;
    }

    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var memberName = member.getName();

        if (memberName == null || memberName.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or blank");
        }

        if (!NAME_PATTERN.matcher(memberName).matches()) {
            throw new IllegalArgumentException("Name can only contain letters and spaces");
        }

        if (memberName.length() < MIN_NAME_LENGTH) {
            throw new IllegalArgumentException("Name must be at least " + MIN_NAME_LENGTH + " characters long");
        }

        if (memberName.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("Name cannot be longer than " + MAX_NAME_LENGTH + " characters");
        }
    }
}
