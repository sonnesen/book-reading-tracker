package com.sonnesen.book_reading_tracker.application.domain.member;

import com.sonnesen.book_reading_tracker.application.domain.email.Email;

public class Member {

    private MemberId memberId;
    private String name;
    private Email email;

    private Member(final MemberId memberId, final String name, final Email email) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;

        validate();
    }

    public static Member newMember(final String name, final String email) {
        return new Member(MemberId.newId(), name, Email.from(email));
    }

    public static Member with(String id, String name, String email) {
        return new Member(MemberId.from(id), name, Email.from(email));
    }

    public Member updateMember(final String name, final String email) {
        this.name = name;
        this.email = Email.from(email);

        validate();

        return this;
    }

    public MemberId getMemberId() {
        return memberId;
    }

    public String getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    private void validate() {
        new MemberValidator(this).validate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return memberId.equals(member.memberId);
    }

    @Override
    public int hashCode() {
        return memberId.hashCode();
    }

}
