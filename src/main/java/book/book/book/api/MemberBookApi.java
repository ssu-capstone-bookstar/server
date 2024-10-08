package book.book.book.api;

import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.dto.savedBookCommentRequest;
import book.book.book.service.MemberBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/memberbook/{bookId}")
@RequiredArgsConstructor
public class MemberBookApi {

    private final MemberBookService memberBookService;

    @PostMapping("/reading-status")
    public void saveReadingStatus(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long bookId,
                                  @RequestBody @Valid SaveReadingStatusRequest request) {
        memberBookService.saveReadingStatus(memberId, bookId, request);
    }

    @DeleteMapping("/reading-status")
    public void deleteReadingStatus(@AuthenticationPrincipal Long memberId,
                                    @PathVariable Long bookId) {
        memberBookService.deleteReadingStatus(memberId, bookId);
    }

    @PostMapping("/book-comment")
    public void saveComment(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long bookId,
                                  @RequestBody @Valid savedBookCommentRequest request) {
        memberBookService.saveBookComment(memberId, bookId, request);
    }
}
