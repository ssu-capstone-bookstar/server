package book.book.collection.api;

import book.book.collection.dto.BookCollectionResponse;
import book.book.collection.dto.SaveBookCollectionRequest;
import book.book.collection.dto.UpdateBookCollectionRequest;
import book.book.collection.service.BookCollectionLikeService;
import book.book.collection.service.BookCollectionService;
import book.book.common.ResponseForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/bookcollections")
@RequiredArgsConstructor
public class BookCollectionApi {
    private final BookCollectionLikeService bookCollectionLikeService;
    private final BookCollectionService bookCollectionService;

    /**
     * Collection 관련
     */

    @PostMapping("/bookcollection")
    public void saveBookCollection(@AuthenticationPrincipal Long memberId,
                                   @RequestBody @Valid SaveBookCollectionRequest request) {
        bookCollectionService.saveBookCollection(memberId, request);
    }

    @PostMapping("/{bookcollection_id}")
    public ResponseForm<BookCollectionResponse> updateBookCollection(@AuthenticationPrincipal Long memberId,
                                                                     @PathVariable Long bookcollection_id,
                                                                     @RequestBody @Valid UpdateBookCollectionRequest request) {
        return new ResponseForm<>(bookCollectionService.updateBookCollection(memberId, bookcollection_id, request));
    }

    @DeleteMapping("/{bookcollection_id}")
    public void deleteBookCollection(@AuthenticationPrincipal Long memberId,
                                     @PathVariable Long bookcollection_id) {
        bookCollectionService.deleteBookCollection(memberId, bookcollection_id);
    }


    /**
     * Collection Like 관련
     */

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

    @PostMapping("/like/bookcollection")
    public void getLikedBookCollection(@AuthenticationPrincipal Long memberId) {
        bookCollectionLikeService.getLikedBookCollection(memberId);
    }
}