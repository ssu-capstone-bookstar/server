package book.book.collection.api;

import book.book.collection.dto.CollectionBookResponse;
import book.book.collection.service.CollectionBookService;
import book.book.common.Response.CursorPageResponse;
import book.book.common.Response.ResponseForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/collections")
@RequiredArgsConstructor
public class CollectionBookApi {

    private final CollectionBookService collectionBookService;

    @GetMapping("{collection_id}/books")
    public ResponseForm<CursorPageResponse<CollectionBookResponse>> getMyCollectionBooks(@PathVariable Long collection_id,
                                                                                     @RequestParam(required = false) Long cursorId) {

        return new ResponseForm<>(collectionBookService.getMyCollectionBooks(collection_id, cursorId));
    }
}
