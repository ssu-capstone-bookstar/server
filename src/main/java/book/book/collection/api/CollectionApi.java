package book.book.collection.api;

import book.book.collection.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("api/v1/memberbook/collections")
@RequiredArgsConstructor
public class CollectionApi {
    private final CollectionService collectionService;

    @PostMapping("/{collection_id}/like")
    public void saveCollectionLike(@AuthenticationPrincipal Long memberId,
                                   @PathVariable Long collection_id) {
        collectionService.saveCollectionLike(memberId, collection_id);
    }

    @DeleteMapping("/{collection_id}/like")
    public void deleteCollectionLike(@AuthenticationPrincipal Long memberId,
                                   @PathVariable Long collection_id) {
        collectionService.deleteCollectionLike(memberId, collection_id);
    }
}