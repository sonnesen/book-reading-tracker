package com.sonnesen.book_reading_tracker.infrastructure.mapper;

import com.sonnesen.book_reading_tracker.application.domain.member.Member;
import com.sonnesen.book_reading_tracker.infrastructure.adapters.outbound.database.entity.MemberJpaEntity;
import com.sonnesen.books.model.MemberDTO;

public class MemberMapper {

    public static MemberJpaEntity toEntity(Member member) {
        if (member == null) {
            return null;
        }

        final var entity = new MemberJpaEntity();
        entity.setId(member.getMemberId().getValue());
        entity.setName(member.getName());
        entity.setEmail(member.getEmail().getValue());

        return entity;
    }

    public static Member toDomain(MemberJpaEntity entity) {
        if (entity == null) {
            return null;
        }

        return Member.with(
                entity.getId(),
                entity.getName(),
                entity.getEmail());
    }

    public static MemberDTO toDTO(final Member createdMember) {
        if (createdMember == null) {
            return null;
        }

        final var dto = new MemberDTO()
            .id(createdMember.getMemberId().getValue())
            .name(createdMember.getName())
            .email(createdMember.getEmail().getValue());

        return dto;
    }

}
