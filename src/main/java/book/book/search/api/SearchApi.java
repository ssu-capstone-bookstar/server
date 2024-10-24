package book.book.search.api;

import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import book.book.search.dto.SearchBookResponse;
import book.book.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/search")
@RequiredArgsConstructor
public class SearchApi {

    private final SearchService searchService;

    @Operation(
            summary = "알라딘 API로 도서 검색"
    )
    @GetMapping("/books/aladin")
    public ResponseForm<CursorPageResponse<SearchBookResponse>> getBooksByAladin(
            @Parameter(name = "query", description = "검색어")
            String query,
            @Parameter(name = "start", description = "시작 페이지")
            @RequestParam int start) {
        return new ResponseForm<>(searchService.getBooksByAladin(query, start));
    }

}