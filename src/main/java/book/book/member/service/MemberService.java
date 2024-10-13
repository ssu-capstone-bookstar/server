package book.book.member.service;

import book.book.member.dto.MinimmumMemberProfile;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public MinimmumMemberProfile getMinimmumMemberProfile(Long memberId) {
        return memberRepository.findNickNameAndImage(memberId);
    }
}
