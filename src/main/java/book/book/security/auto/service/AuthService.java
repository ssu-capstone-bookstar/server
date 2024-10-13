package book.book.security.auto.service;


import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import book.book.member.entity.MemberRole;
import book.book.member.repository.MemberRepository;
import book.book.security.auto.dao.RefreshTokenRepository;
import book.book.security.auto.domain.RefreshToken;
import book.book.security.auto.dto.AuthDto;
import book.book.security.oauth.KakaoUserInfo;
import book.book.security.auto.dto.RegisterRequest;
import book.book.security.auto.dto.AuthResponse;
import book.book.security.jwt.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthService {

    private final JwtUtil jwtUtil;

    private final RefreshTokenRepository refreshTokenRepository;
    private final MemberRepository memberRepository;
    private final KakaoOauth2Service kakaoOauth2Service;



    public AuthResponse register(RegisterRequest request, String authorizationHeader) {

        String oauth2Token = extractToken(authorizationHeader);
        KakaoUserInfo kakaoUserInfo = kakaoOauth2Service.getUserInfo(oauth2Token);

        Member member = saveMember(kakaoUserInfo);

        return generateResponse(member);
    }


    public AuthResponse renewAccessToken(String refreshTokenWithBearer) {
        String refreshToken = extractToken(refreshTokenWithBearer);
        String providerId = jwtUtil.getProviderId(refreshToken);
        Member member = memberRepository.findByProviderId(providerId)
                .orElseThrow(() -> new CustomException(ResultCode.MEMBER_NOT_FOUND));

        RefreshToken storedToken = refreshTokenRepository.findByMemberId(member.getId())
                .orElseThrow(() -> new CustomException(ResultCode.REFRESHTOKEN_NOT_FOUND));

        if (storedToken.getRefreshToken().equals(refreshToken)) {
            return generateResponse(member);
        }
        throw new CustomException(ResultCode.REFRESHTOKEN_OUTDATED);

    }



    public String extractToken(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        throw new IllegalArgumentException("Invalid Authorization header format");
    }

    public AuthResponse generateResponse(Member member){
        AuthDto authDto = AuthDto.builder()
                .id(member.getId())
                .providerId(member.getProviderId())
                .email(member.getEmail())
                .role(member.getRole())
                .build();
        String accessToken = jwtUtil.generateAccessToken(authDto);
        String refreshToken = jwtUtil.generateRefreshToken(authDto);

        refreshTokenRepository.save(new RefreshToken(member.getId(), refreshToken));


        return AuthResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .accessTokenExpiration(jwtUtil.parseExpiration(accessToken))
                .refreshTokenExpiration(jwtUtil.parseExpiration(refreshToken))
                .build();
    }


    private Member saveMember(KakaoUserInfo kakaoUserInfo) {

        Member member = Member.builder()
                .providerId(kakaoUserInfo.getProviderId())
                .email(kakaoUserInfo.getEmail())
                .nickname(kakaoUserInfo.getName())
                .role(MemberRole.USER)
                .build();

        return memberRepository.save(member);
    }



}
