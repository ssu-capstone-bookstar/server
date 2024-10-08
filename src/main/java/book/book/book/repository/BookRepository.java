package book.book.book.repository;

import book.book.book.entity.Book;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findById(Long bookId);

    default Book findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ResultCode.BOOK_NOT_FOUND));
    }

    Optional<Book> findByIsbn(String isbn);

    default Book findByIsbnOrElseThrow(String isbn) {
        return findByIsbn(isbn)
                .orElseThrow(() -> new CustomException(ResultCode.BOOK_NOT_FOUND));
    }
}
