package com.sonnesen.book_reading_tracker.application.domain.pagination;

import java.util.List;
import java.util.function.Function;

public record Pagination<T>(int currentPage, int pageSize, long totalItems, List<T> items) {

    public <R> Pagination<R> mapItems(Function<T, R> mapper) {
        return new Pagination<>(
                currentPage,
                pageSize,
                totalItems,
                items.stream().map(mapper).toList()
        );
    }
}
