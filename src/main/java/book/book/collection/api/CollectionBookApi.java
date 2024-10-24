package book.book.collection.api;

import book.book.book.sort.SortType;
import book.book.collection.dto.CollectionBookResponse;
import book.book.collection.service.CollectionBookService;
import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/collections")
@RequiredArgsConstructor
@Tag(name = "CollectionBook", description = "컬렉션 내 책 API")
public class CollectionBookApi {

    private final CollectionBookService collectionBookService;

    @Operation(summary = "나의 특정 컬렉션 책들 조회")
    @GetMapping("{collectionId}/books")
    public ResponseForm<CursorPageResponse<CollectionBookResponse>> getMyCollectionBooks(
            @PathVariable("collectionId")
            Long collectionId,
            @RequestParam(value = "sortType")
            SortType sortType,
            @RequestParam(value = "cursorId", required = false)
            Long cursorId) {

        return new ResponseForm<>(collectionBookService.getMyCollectionBooks(collectionId, sortType, cursorId));
    }
}
