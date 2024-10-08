package book.book.collection.api;

import book.book.collection.dto.SaveBookCollectionRequest;
import book.book.collection.service.BookCollectionLikeService;
import book.book.collection.service.BookCollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/v1/bookcollections")
@RequiredArgsConstructor
public class BookCollectionApi {
    private final BookCollectionLikeService bookCollectionLikeService;
    private final BookCollectionService bookCollectionService;

    @PostMapping("/bookcollection")
    public void saveCollection(@AuthenticationPrincipal Long memberId,
                               @RequestBody SaveBookCollectionRequest request) {
        bookCollectionService.saveBookCollection(memberId, request);
    }

    @PostMapping("/{bookcollection_id}/like")
    public void saveBookCollectionLike(@AuthenticationPrincipal Long memberId,
                                   @PathVariable Long bookcollection_id) {
        bookCollectionLikeService.saveBookCollectionLike(memberId, bookcollection_id);
    }

    @DeleteMapping("/{bookcollection_id}/like")
    public void deleteBookCollectionLike(@AuthenticationPrincipal Long memberId,
                                     @PathVariable Long bookcollection_id) {
        bookCollectionLikeService.deleteBookCollectionLike(memberId, bookcollection_id);
    }
}