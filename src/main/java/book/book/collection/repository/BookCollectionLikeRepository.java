package book.book.collection.repository;

import book.book.collection.entity.BookCollection;
import book.book.collection.entity.BookCollectionLike;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookCollectionLikeRepository extends JpaRepository<BookCollectionLike, Long> {
    Optional<BookCollectionLike> findByMemberAndCollection(Member member, BookCollection bookCollection);

    Optional<BookCollectionLike> deleteByMemberAndCollection(Member member, BookCollection bookCollection);
}
