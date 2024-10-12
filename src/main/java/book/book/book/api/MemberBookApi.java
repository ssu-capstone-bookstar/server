package book.book.book.api;

import book.book.book.dto.MemberBookResponse;
import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.entity.ReadingStatus;
import book.book.book.service.MemberBookService;
import book.book.book.sort.SortType;
import book.book.common.Response.CursorPageResponse;
import book.book.common.Response.ResponseForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/memberbooks")
@RequiredArgsConstructor
public class MemberBookApi {

    private final MemberBookService memberBookService;

    @GetMapping("/reading_status")
    public ResponseForm<CursorPageResponse<MemberBookResponse>> getBookByReadingStatus(
            @AuthenticationPrincipal Long memberId,
            @RequestParam
            ReadingStatus readingStatus,
            @RequestParam
            SortType sort,
            @RequestParam(required = false)
            Long cursorId) {

        return new ResponseForm<>(memberBookService.getBookByReadingStatus(memberId, readingStatus, sort, cursorId));
    }

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
