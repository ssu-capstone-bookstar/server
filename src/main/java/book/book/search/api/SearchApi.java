package book.book.search.api;

import book.book.common.response.CursorPageResponse;
import book.book.common.response.ResponseForm;
import book.book.search.dto.SearchBookResponse;
import book.book.search.service.SearchService;
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

    @GetMapping("/comment/me")
    public ResponseForm<CursorPageResponse<SearchBookResponse>> getBooksByAladin(@RequestParam String query,
                                                                                 @RequestParam int start) {
        return new ResponseForm<>(searchService.getBooksByAladin(query, start));
    }

}