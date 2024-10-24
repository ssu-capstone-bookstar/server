package book.book.book.api;

import book.book.book.dto.MemberBookResponse;
import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.entity.ReadingStatus;
import book.book.book.service.MemberBookService;
import book.book.book.sort.SortType;
import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/memberbooks")
@RequiredArgsConstructor
@Tag(name = "MemberBook", description = "서재 내 책 API")
public class MemberBookApi {

    private final MemberBookService memberBookService;

    @Operation(summary = "서재에서 읽은 상태에 따른 책 조회")
    @GetMapping("/reading-status")
    public ResponseForm<CursorPageResponse<MemberBookResponse>> getBookByReadingStatus(
            @AuthenticationPrincipal
            Long memberId,
            @RequestParam(value = "readingStatus")
            ReadingStatus readingStatus,
            @RequestParam(value = "sortType")
            SortType sortType,
            @RequestParam(value = "cursorId", required = false)
            Long cursorId) {

        return new ResponseForm<>(memberBookService.getBookByReadingStatus(memberId, readingStatus, sortType, cursorId));
    }

    @Operation(summary = "서재에 읽은 상태에 따른 책 생성")
    @PostMapping("/{bookId}/reading-status")
    public void saveReadingStatus(@AuthenticationPrincipal Long memberId,
                                  @PathVariable("bookId") Long bookId,
                                  @RequestBody @Valid SaveReadingStatusRequest request) {
        memberBookService.saveReadingStatus(memberId, bookId, request);
    }

    @Operation(summary = "서재에서 읽은 상태에 따른 책 삭제")
    @DeleteMapping("/{bookId}/reading-status")
    public void deleteReadingStatus(@AuthenticationPrincipal Long memberId,
                                    @PathVariable("bookId") Long bookId) {
        memberBookService.deleteReadingStatus(memberId, bookId);
    }


}
