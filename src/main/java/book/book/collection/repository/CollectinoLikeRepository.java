package book.book.collection.repository;

import book.book.collection.entity.Collection;
import book.book.collection.entity.CollectionLike;
import book.book.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectinoLikeRepository extends JpaRepository<CollectionLike, Long> {
    Optional<CollectionLike> findByMemberAndCollection(Member member, Collection collection);
}
