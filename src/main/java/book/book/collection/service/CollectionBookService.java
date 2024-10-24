package book.book.collection.service;

import book.book.book.entity.Book;
import book.book.book.repository.BookRepository;
import book.book.book.service.MappingMemberBookAndImage;
import book.book.book.sort.SortType;
import book.book.collection.dto.CollectionBookResponse;
import book.book.collection.dto.MinmumBookInfoRequest;
import book.book.collection.entity.Collection;
import book.book.collection.entity.CollectionBook;
import book.book.collection.repository.collectionbook.CollectionBookRepository;
import book.book.common.response.CursorPageResponse;
import book.book.image.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CollectionBookService {

    private static final Integer PAGE_SIZE = 12;
    private final CollectionBookRepository collectionBookRepository;
    private final BookRepository bookRepository;
    private final ImageService imageService;

    @Transactional
    public void saveCollectionBooks(Collection collection,
                                    List<MinmumBookInfoRequest> minmumBookInfoRequests) {
        List<CollectionBook> collectionBooks = new ArrayList<>();

        for (MinmumBookInfoRequest minmumBookInfoRequest : minmumBookInfoRequests) {
            Book book = bookRepository.findByIsbn13OrElseThrow(minmumBookInfoRequest.isbn13());
            collectionBooks.add(new CollectionBook(collection, book));
        }
        collectionBookRepository.saveAll(collectionBooks);
    }

    @Transactional
    public void deleteAllByCollection(Collection collection) {
        collectionBookRepository.deleteAllByCollection(collection);
    }

    @Transactional(readOnly = true)
    public CursorPageResponse<CollectionBookResponse> getMyCollectionBooks(Long collectionId, SortType sortType, Long cursorId) {
        List<CollectionBook> collectionBooks = collectionBookRepository.findBooks(collectionId, sortType, cursorId, PAGE_SIZE);
        List<Long> bookIds = getBookIds(collectionBooks);

        Map<Long, String> imagesMap = imageService.getAllByBookIds(bookIds);

        List<CollectionBookResponse> collectionBookResponses = MappingMemberBookAndImage.mappingCollectinoBook(collectionBooks, imagesMap);

        return CursorPageResponse.of(collectionBookResponses, PAGE_SIZE, CollectionBookResponse::getCollectionBookId);

    }

    private List<Long> getBookIds(List<CollectionBook> collectionBooks) {
        return collectionBooks.stream()
                .map(collectionBook -> collectionBook.getBook().getId())
                .toList();
    }
}
