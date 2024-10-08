package book.book.book.repository;

import book.book.book.entity.Book;
import book.book.book.entity.MemberBook;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberBookRepository extends JpaRepository<MemberBook, Long> {

    Optional<MemberBook> findById(Long memberBookId);

    default MemberBook findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ResultCode.MEMBERBOOK_NOT_FOUND));
    }

    Optional<MemberBook> findByMemberAndBook(Member member, Book book);

    void deleteByMemberAndBook(Member member, Book book);
}
