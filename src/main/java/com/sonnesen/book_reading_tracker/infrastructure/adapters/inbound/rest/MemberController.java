package com.sonnesen.book_reading_tracker.infrastructure.adapters.inbound.rest;

import java.net.URI;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.sonnesen.book_reading_tracker.application.ports.inbound.MemberService;
import com.sonnesen.book_reading_tracker.infrastructure.mapper.MemberMapper;
import com.sonnesen.books.api.MembersApi;
import com.sonnesen.books.model.CreateMemberRequestDTO;
import com.sonnesen.books.model.MemberDTO;
import com.sonnesen.books.model.PaginatedMembersDTO;
import com.sonnesen.books.model.UpdateMemberRequestDTO;

@RestController
public class MemberController implements MembersApi {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public ResponseEntity<MemberDTO> createMember(CreateMemberRequestDTO createMemberRequestDTO) {
        final var createdMember = memberService.create(createMemberRequestDTO.getName(),
                createMemberRequestDTO.getEmail());
        final var uri = URI.create("/members/" + createdMember.getMemberId().getValue());

        return ResponseEntity.created(uri).body(MemberMapper.toDTO(createdMember));
    }

    @Override
    public ResponseEntity<Void> deleteMember(final String memberId) {
        memberService.delete(memberId);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<MemberDTO> getMemberById(final String memberId) {
        final var member = memberService.get(memberId);
        return ResponseEntity.ok(MemberMapper.toDTO(member));
    }

    @Override
    public ResponseEntity<PaginatedMembersDTO> listMembers(final Integer page,
            final Integer perPage, final String name, final String email) {
        final var members = memberService.list(page, perPage, name, email);
        final var dto = new PaginatedMembersDTO()
                .page(members.currentPage())
                .perPage(members.pageSize())
                .totalPages(members.totalItems())
                .items(members.items().stream().map(MemberMapper::toDTO).toList());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<MemberDTO> updateMember(final String memberId,
            final UpdateMemberRequestDTO updateMemberRequestDTO) {
        final var updatedMember = memberService.update(memberId, updateMemberRequestDTO.getName(),
                updateMemberRequestDTO.getEmail());
        return ResponseEntity.ok(MemberMapper.toDTO(updatedMember));
    }

}
