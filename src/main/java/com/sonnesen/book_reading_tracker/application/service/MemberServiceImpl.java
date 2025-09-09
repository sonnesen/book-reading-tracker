package com.sonnesen.book_reading_tracker.application.service;

import java.util.Objects;
import org.springframework.stereotype.Service;
import com.sonnesen.book_reading_tracker.application.domain.exception.DomainEntityNotFoundException;
import com.sonnesen.book_reading_tracker.application.domain.exception.EmailAlreadyInUseException;
import com.sonnesen.book_reading_tracker.application.domain.member.Member;
import com.sonnesen.book_reading_tracker.application.domain.pagination.Pagination;
import com.sonnesen.book_reading_tracker.application.ports.inbound.MemberService;
import com.sonnesen.book_reading_tracker.application.ports.outbound.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    public MemberServiceImpl(final MemberRepository memberRepository) {
        Objects.requireNonNull(memberRepository);
        this.memberRepository = memberRepository;
    }

    @Override
    public Member create(final String name, final String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }

        final var newMember = Member.newMember(name, email);
        return memberRepository.create(newMember);
    }

    @Override
    public void delete(final String memberId) {
        memberRepository.delete(memberId);
    }

    @Override
    public Member get(final String memberId) {
        return memberRepository.get(memberId)
                .orElseThrow(() -> new DomainEntityNotFoundException("Member", memberId));
    }

    @Override
    public Pagination<Member> list(final int page, final int size, final String name,
            final String email) {
        return memberRepository.find(page, size, name, email);
    }

    @Override
    public Member update(final String memberId, final String name, final String email) {
        final var member = get(memberId);

        if (memberRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException(email);
        }

        member.updateMember(name, email);

        return memberRepository.update(member);
    }
}
