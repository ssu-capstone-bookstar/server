package book.book.book.api;

import book.book.book.dto.MyCommentResponse;
import book.book.book.dto.SavedBookCommentRequest;
import book.book.book.service.bookCommentService;
import book.book.book.sort.SortType;
import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class BookCommentApi {

    private final bookCommentService bookCommentService;

    @GetMapping("/comment/me")
    public ResponseForm<CursorPageResponse<MyCommentResponse>> getMyComments(@AuthenticationPrincipal
                                                                             Long memberId,
                                                                             @RequestParam
                                                                             SortType sortType,
                                                                             @RequestParam(required = false) Long cursorId) {
        return new ResponseForm<>(bookCommentService.getMyComments(memberId, sortType, cursorId));
    }

    @PostMapping("/book_comment/{book_id}")
    public void saveBookComment(@AuthenticationPrincipal Long memberId,
                                @PathVariable Long book_id,
                                @RequestBody @Valid SavedBookCommentRequest request) {
        bookCommentService.saveBookComment(memberId, book_id, request);
    }


    @DeleteMapping("/book_comment/{book_id}")
    public void deleteBookComment(@AuthenticationPrincipal Long memberId,
                                  @PathVariable Long book_id) {
        bookCommentService.deleteBookComment(memberId, book_id);
    }

    @PostMapping("/{book_comment_id}/like")
    public void saveBookCommentLike(@AuthenticationPrincipal Long memberId,
                                    @PathVariable Long book_comment_id) {
        bookCommentService.saveBookCommentLike(memberId, book_comment_id);
    }

    @DeleteMapping("/{book_comment_id}/like")
    public void deleteBookCommentLike(@AuthenticationPrincipal Long memberId,
                                      @PathVariable Long book_comment_id) {
        bookCommentService.deleteBookCommentLike(memberId, book_comment_id);
    }
}