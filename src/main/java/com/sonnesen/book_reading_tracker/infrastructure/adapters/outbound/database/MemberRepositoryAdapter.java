package com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database;

import java.util.Objects;
import java.util.Optional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import com.sonnesen.book_reading_tracker.application.domain.member.Member;
import com.sonnesen.book_reading_tracker.application.domain.pagination.Pagination;
import com.sonnesen.book_reading_tracker.application.ports.outbound.MemberRepository;
import com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database.repository.MemberJpaRepository;
import com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database.repository.MemberJpaSpecification;
import com.sonnesen.book_reading_tracker.infrastructure.mapper.MemberMapper;

@Repository
public class MemberRepositoryAdapter implements MemberRepository {

    private final MemberJpaRepository memberJpaRepository;

    public MemberRepositoryAdapter(final MemberJpaRepository memberJpaRepository) {
        Objects.requireNonNull(memberJpaRepository);
        this.memberJpaRepository = memberJpaRepository;
    }

    @Override
    public Member create(final Member member) {
        final var toCreate = MemberMapper.toEntity(member);
        final var savedEntity = memberJpaRepository.save(toCreate);

        return MemberMapper.toDomain(savedEntity);
    }

    @Override
    public void delete(final String memberId) {
        memberJpaRepository.deleteById(memberId);
    }

    @Override
    public Pagination<Member> find(final int pageNumber, final int pageSize, final String name,
            final String email) {
        final var withPage = Pageable.ofSize(pageSize).withPage(pageNumber);
        final var withSpec = MemberJpaSpecification.create(name, email);
        final var page = memberJpaRepository.findAll(withSpec, withPage);
        final var pagination = new Pagination<Member>(page.getNumber(), page.getSize(),
                page.getTotalElements(), page.map(MemberMapper::toDomain).toList());

        return pagination;
    }

    @Override
    public Optional<Member> get(final String memberId) {
        return memberJpaRepository.findById(memberId).map(MemberMapper::toDomain);
    }

    @Override
    public Member update(final Member member) {
        final var toUpdate = MemberMapper.toEntity(member);
        final var updatedEntity = memberJpaRepository.save(toUpdate);

        return MemberMapper.toDomain(updatedEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberJpaRepository.existsByEmail(email);
    }

}
