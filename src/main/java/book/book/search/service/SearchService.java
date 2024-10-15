package book.book.search.service;

import book.book.book.entity.Book;
import book.book.book.repository.BookRepository;
import book.book.common.Response.CursorPageResponse;
import book.book.search.dto.SearchBookResponse;
import book.book.search.dto.aladin.AladinSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final BookRepository bookRepository;
    private final AladinService aladinService;

    @Transactional
    public CursorPageResponse<SearchBookResponse> getBooksByAladin(String query, Integer start) {
        AladinSearchResponse aladinSearchResponse = aladinService.search(query, start);
        List<AladinSearchResponse.SearchItem> items = aladinSearchResponse.getItem();

        findOrElseSaveBooks(items);

        List<SearchBookResponse> searchBookResponses = items.stream()
                .map(SearchBookResponse::of)
                .toList();

        return CursorPageResponse.ofAladinResponse(searchBookResponses, aladinSearchResponse);
    }

    private void findOrElseSaveBooks(List<AladinSearchResponse.SearchItem> items) {

        for (AladinSearchResponse.SearchItem item : items) {
            bookRepository.findByIsbn13(item.getIsbn13())
                    .orElseGet(() -> bookRepository.save(Book.fromAladinSearchResponse(item)));
        }
    }
}
