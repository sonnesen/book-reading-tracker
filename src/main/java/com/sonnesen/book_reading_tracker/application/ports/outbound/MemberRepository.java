package com.sonnesen.book_reading_tracker.application.ports.outbound;

import java.util.Optional;
import com.sonnesen.book_reading_tracker.application.domain.member.Member;
import com.sonnesen.book_reading_tracker.application.domain.pagination.Pagination;

public interface MemberRepository {

    Member create(Member member);

    Member update(Member member);

    void delete(String memberId);

    Optional<Member> get(String memberId);

    Pagination<Member> find(int page, int size, String name, String email);

    boolean existsByEmail(String email);
}
