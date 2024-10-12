package book.book.recommending.service;

import book.book.book.entity.Book;
import book.book.book.repository.BookRepository;
import book.book.book.service.MappingMemberBookAndImage;
import book.book.collection.dto.MinmumBookInfoRequest;
import book.book.common.Response.CursorPageResponse;
import book.book.image.ImageService;
import book.book.recommending.dto.RecommendingBookResponse;
import book.book.recommending.entity.Recommending;
import book.book.recommending.entity.RecommendingBook;
import book.book.recommending.repository.RecommendingBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * ColectionBook이랑 코드가 겹침 어떻게 통합시킬지 추후 고민
 */
@Service
@RequiredArgsConstructor
public class RecommendingBookService {

    private static final Integer PAGE_SIZE = 12;
    private final RecommendingBookRepository recommendingBookRepository;
    private final BookRepository bookRepository;
    private final ImageService imageService;

    @Transactional
    public void saveCollectionBooks(Recommending Recommending,
                                    List<MinmumBookInfoRequest> minmumBookInfoRequests) {
        List<RecommendingBook> recommendingBooks = new ArrayList<>();

        for (MinmumBookInfoRequest minmumBookInfoRequest : minmumBookInfoRequests) {
            Book book = bookRepository.findByIsbnOrElseThrow(minmumBookInfoRequest.getIsbn());
            recommendingBooks.add(new RecommendingBook(Recommending, book));
        }

        recommendingBookRepository.saveAll(recommendingBooks);
    }

    @Transactional(readOnly = true)
    public CursorPageResponse<RecommendingBookResponse> getRecommendedBooks(Long recommenedId, Long cursorId) {
        List<RecommendingBook> recommendingBooks = recommendingBookRepository.findBooksOnRecommended(recommenedId, cursorId, PAGE_SIZE);
        List<Long> bookIds = getBookIds(recommendingBooks);

        Map<Long, String> imagesMap = imageService.getAllByBookIds(bookIds);

        List<RecommendingBookResponse> recommendingBookResponses = MappingMemberBookAndImage.mappingRecommendingBook(recommendingBooks, imagesMap);

        return CursorPageResponse.of(recommendingBookResponses, PAGE_SIZE, RecommendingBookResponse::getRecommendingBookId);

    }

    private List<Long> getBookIds(List<RecommendingBook> recommendingBooks) {
        return recommendingBooks.stream()
                .map(recommendingBook -> recommendingBook.getBook().getId())
                .toList();
    }
}
