package book.book.book.api;

import book.book.book.dto.MyCommentResponse;
import book.book.book.dto.SavedBookCommentRequest;
import book.book.book.service.bookCommentService;
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
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
@Tag(name = "BookComment", description = "책 평가 API")
public class BookCommentApi {

    private final bookCommentService bookCommentService;

    @Operation(summary = "나의 평가들 조회")
    @GetMapping("/comment/me")
    public ResponseForm<CursorPageResponse<MyCommentResponse>> getMyComments(
            @AuthenticationPrincipal
            Long memberId,
            @RequestParam(value = "sortType")
            SortType sortType,
            @RequestParam(value = "cursorId", required = false)
            Long cursorId) {
        return new ResponseForm<>(bookCommentService.getMyComments(memberId, sortType, cursorId));
    }

    @Operation(summary = "평가 생성")
    @PostMapping("/book_comment/{book_id}")
    public void saveBookComment(@AuthenticationPrincipal Long memberId,
                                @PathVariable Long book_id,
                                @RequestBody @Valid SavedBookCommentRequest request) {
        bookCommentService.saveBookComment(memberId, book_id, request);
    }

    @Operation(summary = "평가 삭제")
    @DeleteMapping("/book_comment/{book_id}")
    public void deleteBookComment(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long book_id) {
        bookCommentService.deleteBookComment(memberId, book_id);
    }

    @Operation(summary = "평가 좋아요 생성")
    @PostMapping("/{book_comment_id}/like")
    public void saveBookCommentLike(@AuthenticationPrincipal Long memberId,
                                    @PathVariable Long book_comment_id) {
        bookCommentService.saveBookCommentLike(memberId, book_comment_id);
    }

    @Operation(summary = "평가 좋아요 삭제")
    @DeleteMapping("/{book_comment_id}/like")
    public void deleteBookCommentLike(@AuthenticationPrincipal Long memberId,
                                      @PathVariable Long book_comment_id) {
        bookCommentService.deleteBookCommentLike(memberId, book_comment_id);
    }
}