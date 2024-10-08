package book.book.collection.repository;

import book.book.collection.entity.BookCollection;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCollectionRepository extends JpaRepository<BookCollection, Long> {

    Optional<BookCollection> findById(Long collectionId);

    default BookCollection findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ResultCode.COLLECTION_NOT_FOUND));
    }

    Optional<BookCollection> findByMemberAndName(Member member, String name);

    boolean existsByMemberAndName(Member member, String name);
}
