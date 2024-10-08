package book.book.book.api;

import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.dto.savedBookCommentRequest;
import book.book.book.service.MemberBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/memberbook/{book_id}")
@RequiredArgsConstructor
public class MemberBookApi {

    private final MemberBookService memberBookService;

    @PostMapping("/reading-status")
    public void saveReadingStatus(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long book_id,
                                  @RequestBody @Valid SaveReadingStatusRequest request) {
        memberBookService.saveReadingStatus(memberId, book_id, request);
    }

    @DeleteMapping("/reading-status")
    public void deleteReadingStatus(@AuthenticationPrincipal Long memberId,
                                    @PathVariable Long book_id) {
        memberBookService.deleteReadingStatus(memberId, book_id);
    }

    @PostMapping("/book-comment")
    public void saveBookComment(@AuthenticationPrincipal Long memberId,
                                @PathVariable Long book_id,
                                @RequestBody @Valid savedBookCommentRequest request) {
        memberBookService.saveBookComment(memberId, book_id, request);
    }


    @DeleteMapping("/book-comment")
    public void deleteBookComment(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long book_id) {
        memberBookService.deleteBookComment(memberId, book_id);
    }
}
