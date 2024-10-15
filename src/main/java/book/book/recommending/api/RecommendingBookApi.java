package book.book.recommending.api;

import book.book.book.sort.SortType;
import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import book.book.recommending.dto.RecommendingBookResponse;
import book.book.recommending.service.RecommendingBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recommeding")
@RequiredArgsConstructor
public class RecommendingBookApi {
    private final RecommendingBookService recommendingBookService;

    @GetMapping("/books/{recommened_id}")
    public ResponseForm<CursorPageResponse<RecommendingBookResponse>> getRecommendedBooks(@PathVariable
                                                                                          Long recommened_id,
                                                                                          @RequestParam
                                                                                          SortType sortType,
                                                                                          @RequestParam(required = false)
                                                                                          Long cursorId) {

        return new ResponseForm<>(recommendingBookService.getRecommendedBooks(recommened_id, sortType, cursorId));
    }
}
