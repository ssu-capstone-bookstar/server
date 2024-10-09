package book.book.recommending.service;

import book.book.book.entity.Book;
import book.book.book.repository.BookRepository;
import book.book.collection.dto.MinmumBookInfoRequest;
import book.book.collection.entity.CollectionBook;
import book.book.recommending.entity.Recommending;
import book.book.recommending.entity.RecommendingBook;
import book.book.recommending.repository.RecommendingBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * ColectionBook이랑 코드가 겹침 어떻게 통합시킬지 추후 고민
 */
@Service
@RequiredArgsConstructor
public class RecommendingBookService {

    private final RecommendingBookRepository recommendingBookRepository;
    private final BookRepository bookRepository;

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

}
