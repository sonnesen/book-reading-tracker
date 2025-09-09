package com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database.repository;

import org.springframework.data.jpa.domain.Specification;
import com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database.entity.MemberJpaEntity;

public class MemberJpaSpecification {

    private MemberJpaSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<MemberJpaEntity> create(final String name, final String email) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            if (name != null && !name.isBlank()) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"));
            }

            if (email != null && !email.isBlank()) {
                predicates = criteriaBuilder.and(predicates,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("email")), "%" + email.toLowerCase() + "%"));
            }

            return predicates;
        };
    }
}
