package com.sonnesen.book_reading_tracker.application.domain.member;

import java.util.Objects;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MemberId {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberId.class);

    private final String value;

    private MemberId(String value) {
        Objects.requireNonNull(value, "MemberId value cannot be null");
        this.value = value;
    }

    public static MemberId newId() {
        return new MemberId(UUID.randomUUID().toString());
    }

    public static MemberId from(final String value) {
        try {
            return new MemberId(UUID.fromString(value).toString());
        } catch (final IllegalArgumentException e) {
            LOGGER.error("Failed to create MemberId from value: {}", value);
            throw new IllegalArgumentException("Invalid member id");
        }
    }

    public String getValue() {
        return value;
    }

    @Override
    public final String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final MemberId other = (MemberId) obj;
        return value.equals(other.value);
    }

}
