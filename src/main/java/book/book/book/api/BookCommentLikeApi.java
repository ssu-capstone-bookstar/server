package book.book.book.api;

import book.book.book.service.BookCommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/memberbook")
@RequiredArgsConstructor
public class BookCommentLikeApi {

    private final BookCommentLikeService bookCommentLikeService;

    @PostMapping("/{book_comment_id}/like")
    public void saveBookCommentLike(@AuthenticationPrincipal Long memberId,
                                    @PathVariable Long book_comment_id) {
        bookCommentLikeService.saveBookCommentLike(memberId, book_comment_id);
    }

    @DeleteMapping("/{book_comment_id}/like")
    public void deleteBookCommentLike(@AuthenticationPrincipal Long memberId,
                                      @PathVariable Long book_comment_id) {
        bookCommentLikeService.deleteBookCommentLike(memberId, book_comment_id);
    }
}