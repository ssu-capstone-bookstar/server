package book.book.book.api;

import book.book.book.dto.SavedBookCommentRequest;
import book.book.book.service.bookCommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/comments")
@RequiredArgsConstructor
public class BookCommentApi {

    private final bookCommentService bookCommentService;

    @PostMapping("/book-comment")
    public void saveBookComment(@AuthenticationPrincipal Long memberId,
                                @PathVariable Long book_id,
                                @RequestBody @Valid SavedBookCommentRequest request) {
        bookCommentService.saveBookComment(memberId, book_id, request);
    }


    @DeleteMapping("/book-comment")
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