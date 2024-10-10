package book.book.book.api;

import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.dto.SavedBookCommentRequest;
import book.book.book.service.MemberBookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/memberbooks")
@RequiredArgsConstructor
public class MemberBookApi {

    private final MemberBookService memberBookService;

    @PostMapping("/{book_id}/reading_status")
    public void saveReadingStatus(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long book_id,
                                  @RequestBody @Valid SaveReadingStatusRequest request) {
        memberBookService.saveReadingStatus(memberId, book_id, request);
    }

    @DeleteMapping("/{book_id}/reading_status")
    public void deleteReadingStatus(@AuthenticationPrincipal Long memberId,
                                    @PathVariable Long book_id) {
        memberBookService.deleteReadingStatus(memberId, book_id);
    }


}
