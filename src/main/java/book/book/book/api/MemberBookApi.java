package book.book.book.api;

import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.service.MemberBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/memberbook")
@RequiredArgsConstructor
public class MemberBookApi {

    private final MemberBookService memberBookService;

    @PostMapping("/reading-status/{bookId}")
    public void saveReadingStatus(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long bookId,
                                  @RequestBody @Valid SaveReadingStatusRequest request) {
        memberBookService.saveReadingStatus(memberId, bookId,request);
    }
}
