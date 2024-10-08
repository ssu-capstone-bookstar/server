package book.book.collection.service;

import book.book.book.entity.Book;
import book.book.book.repository.BookRepository;
import book.book.collection.dto.BookInfoRequest;
import book.book.collection.entity.Collection;
import book.book.collection.entity.CollectionBook;
import book.book.collection.repository.CollectionBookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CollectionBookService {

    private final CollectionBookRepository collectionBookRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void saveCollectionBooks(Collection collection,
                                         List<BookInfoRequest> bookInfoRequests) {
        List<CollectionBook> collectionBooks =  new ArrayList<>();

        for (BookInfoRequest bookInfoRequest : bookInfoRequests) {
            Book book = bookRepository.findByIsbnOrElseThrow(bookInfoRequest.getIsbn());
            collectionBooks.add(new CollectionBook(collection, book));
        }
        collectionBookRepository.saveAll(collectionBooks);
    }

    @Transactional
    public void deleteAllByCollection(Collection collection) {
        collectionBookRepository.deleteAllByBookCollection(collection);
    }
}
