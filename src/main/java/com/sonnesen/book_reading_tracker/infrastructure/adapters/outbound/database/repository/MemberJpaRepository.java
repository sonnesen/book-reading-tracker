package com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database.entity.MemberJpaEntity;

public interface MemberJpaRepository extends JpaRepository<MemberJpaEntity, String>, JpaSpecificationExecutor<MemberJpaEntity> {

    boolean existsByEmail(String value);

}
