package book.book.member.api;

import book.book.common.response.ResponseForm;
import book.book.member.dto.MinimmumMemberProfile;
import book.book.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
@Tag(name = "Member", description = "맴버 API")
public class MemberApi {

    private final MemberService memberService;

    @Operation(summary = "맴버 닉네임과 프로필이미지 조회")
    @GetMapping("/profile")
    public ResponseForm<MinimmumMemberProfile> getMinimmumMemberProfile(@AuthenticationPrincipal Long memberId) {
        return new ResponseForm<>(memberService.getMinimmumMemberProfile(memberId));
    }
}
