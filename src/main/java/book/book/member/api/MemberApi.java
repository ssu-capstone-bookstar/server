package book.book.member.api;

import book.book.common.response.ResponseForm;
import book.book.member.dto.MinimmumMemberProfile;
import book.book.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberApi {

    private final MemberService memberService;

    @GetMapping("/profile")
    public ResponseForm<MinimmumMemberProfile> getMinimmumMemberProfile(@AuthenticationPrincipal Long memberId) {
        return new ResponseForm<>(memberService.getMinimmumMemberProfile(memberId));
    }
}
