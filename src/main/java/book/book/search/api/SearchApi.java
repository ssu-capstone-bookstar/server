package book.book.search.api;

import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import book.book.search.dto.SearchBookResponse;
import book.book.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
@Tag(name = "Search", description = "검색 API")
public class SearchApi {

    private final SearchService searchService;

    @Operation(summary = "알라딘 API로 도서 검색")
    @GetMapping("/books/aladin")
    public ResponseForm<CursorPageResponse<SearchBookResponse>> getBooksByAladin(
            @RequestParam(value = "query")
            String query,
            @RequestParam(value = "start")
            int start) {
        return new ResponseForm<>(searchService.getBooksByAladin(query, start));
    }

}