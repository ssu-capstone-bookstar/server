package book.book.book.repository;

import book.book.book.entity.Book;
import book.book.book.entity.Rating;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findById(Long memberBookId);

    default Rating findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ResultCode.MEMBER_BOOK_NOT_FOUND));
    }

    Optional<Rating> findByMemberAndBook(Member member, Book book);
}
