package book.book.collection.api;

import book.book.collection.dto.CollectionResponse;
import book.book.collection.dto.CollectionResponses;
import book.book.collection.dto.SaveCollectionRequest;
import book.book.collection.dto.UpdateCollectionRequest;
import book.book.collection.service.CollectionLikeService;
import book.book.collection.service.CollectionService;
import book.book.common.response.ResponseForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/collections")
@RequiredArgsConstructor
@Tag(name = "Collection", description = "컬렉션 API")
public class CollectionApi {

    private final CollectionLikeService collectionLikeService;
    private final CollectionService collectionService;

    /**
     * Collection 관련
     */

    @Operation(summary = "컬렉션 생성")
    @PostMapping("/collection")
    public void saveCollection(@AuthenticationPrincipal Long memberId,
                               @RequestBody @Valid SaveCollectionRequest request) {
        collectionService.saveCollection(memberId, request);
    }

    @Operation(summary = "컬렉션 정보 업데이트")
    @PostMapping("/{collectionId}")
    public ResponseForm<CollectionResponse> updateCollection(@AuthenticationPrincipal Long memberId,
                                                             @PathVariable("collectionId") Long collectionId,
                                                             @RequestBody @Valid UpdateCollectionRequest request) {
        return new ResponseForm<>(collectionService.updateCollection(memberId, collectionId, request));
    }

    @Operation(summary = "컬렉션 삭제")
    @DeleteMapping("/{collectionId}")
    public void deleteCollection(@AuthenticationPrincipal Long memberId,
                                 @PathVariable("collectionId") Long collectionId) {
        collectionService.deleteCollection(memberId, collectionId);
    }

    @Operation(summary = "내 컬렉션들 썸네일 조회")
    @GetMapping("/me")
    public ResponseForm<CollectionResponses> getMyCollections(@AuthenticationPrincipal Long memberId) {
        return new ResponseForm<>(collectionService.getMyCollections(memberId));
    }


    /**
     * Collection Like 관련
     */

    @Operation(summary = "컬렉션 좋아요 생성")
    @PostMapping("/{collectionId}/like")
    public void saveCollectionLike(@AuthenticationPrincipal Long memberId,
                                   @PathVariable("collectionId") Long collectionId) {
        collectionLikeService.saveCollectionLike(memberId, collectionId);
    }

    @Operation(summary = "컬렉션 좋아요 삭제")
    @DeleteMapping("/{collectionId}/like")
    public void deleteCollectionLike(@AuthenticationPrincipal Long memberId,
                                     @PathVariable("collectionId") Long collectionId) {
        collectionLikeService.deleteCollectionLike(memberId, collectionId);
    }

    @Operation(summary = "좋아요한 컬렉션들 썸네일 조회")
    @GetMapping("/like")
    public ResponseForm<CollectionResponses> getLikedCollection(@AuthenticationPrincipal Long memberId) {
        return new ResponseForm<>(collectionLikeService.getLikedCollections(memberId));
    }
}