package book.book.member.repository;

import book.book.member.dto.MinimmumMemberProfile;

public interface MemberRepositoryCustom {
    MinimmumMemberProfile findNickNameAndImage(Long memberId);
}
