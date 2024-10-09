package book.book.collection.api;

import book.book.collection.dto.CollectionResponse;
import book.book.collection.dto.CollectionResponses;
import book.book.collection.dto.SaveCollectionRequest;
import book.book.collection.dto.UpdateCollectionRequest;
import book.book.collection.service.CollectionLikeService;
import book.book.collection.service.CollectionService;
import book.book.common.ResponseForm;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/collections")
@RequiredArgsConstructor
public class CollectionApi {

    private final CollectionLikeService collectionLikeService;
    private final CollectionService collectionService;

    /**
     * Collection 관련
     */

    @PostMapping("/collection")
    public void saveCollection(@AuthenticationPrincipal Long memberId,
                               @RequestBody @Valid SaveCollectionRequest request) {
        collectionService.saveCollection(memberId, request);
    }

    @PostMapping("/{collection_id}")
    public ResponseForm<CollectionResponse> updateCollection(@AuthenticationPrincipal Long memberId,
                                                             @PathVariable Long collection_id,
                                                             @RequestBody @Valid UpdateCollectionRequest request) {
        return new ResponseForm<>(collectionService.updateCollection(memberId, collection_id, request));
    }

    @DeleteMapping("/{collection_id}")
    public void deleteCollection(@AuthenticationPrincipal Long memberId,
                                 @PathVariable Long collection_id) {
        collectionService.deleteCollection(memberId, collection_id);
    }

    @GetMapping("/me")
    public ResponseForm<CollectionResponses> getMyCollections(@AuthenticationPrincipal Long memberId) {
        return new ResponseForm<>(collectionService.getMyCollections(memberId));
    }


    /**
     * Collection Like 관련
     */

    @PostMapping("/{collection_id}/like")
    public void saveCollectionLike(@AuthenticationPrincipal Long memberId,
                                   @PathVariable Long collection_id) {
        collectionLikeService.saveCollectionLike(memberId, collection_id);
    }

    @DeleteMapping("/{collection_id}/like")
    public void deleteCollectionLike(@AuthenticationPrincipal Long memberId,
                                     @PathVariable Long collection_id) {
        collectionLikeService.deleteCollectionLike(memberId, collection_id);
    }

    @GetMapping("/like")
    public ResponseForm<CollectionResponses> getLikedCollection(@AuthenticationPrincipal Long memberId) {
        return new ResponseForm<>(collectionLikeService.getLikedCollections(memberId));
    }
}