package com.sonnesen.book_reading_tracker.application.ports.inbound;

import com.sonnesen.book_reading_tracker.application.domain.member.Member;
import com.sonnesen.book_reading_tracker.application.domain.pagination.Pagination;

public interface MemberService {

    Member create(String name, String email);

    Member update(String memberId, String name, String email);

    void delete(String memberId);

    Member get(String memberId);

    Pagination<Member> list(int page, int size, String name, String email);
}
