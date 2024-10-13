package book.book.member.service;

import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import book.book.security.auto.dao.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;

    public Member findById(Long memberID){
        return memberRepository.findById(memberID).orElseThrow(() -> new IllegalStateException("Unexpected user"));
    }
}
