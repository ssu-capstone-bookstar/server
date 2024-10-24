package book.book.recommending.api;

import book.book.book.sort.SortType;
import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import book.book.recommending.dto.RecommendingBookResponse;
import book.book.recommending.service.RecommendingBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/recommending")
@RequiredArgsConstructor
@Tag(name = "RecommendingBook", description = "추천받은 책 조회 API")
public class RecommendingBookApi {
    private final RecommendingBookService recommendingBookService;

    @Operation(summary = "추천받은 책들 조회")
    @GetMapping("/books/{recommendedId}")
    public ResponseForm<CursorPageResponse<RecommendingBookResponse>> getRecommendedBooks(
            @PathVariable(value = "recommendedId")
            Long recommendedId,
            @RequestParam(value = "sortType")
            SortType sortType,
            @RequestParam(value = "cursorId", required = false)
            Long cursorId) {

        return new ResponseForm<>(recommendingBookService.getRecommendedBooks(recommendedId, sortType, cursorId));
    }
}
