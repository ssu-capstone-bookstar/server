package book.book.recommending.repository;

import book.book.member.entity.Member;
import book.book.recommending.entity.Recommending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecommendingRepository extends JpaRepository<Recommending, Long> {
    Optional<Recommending> findByRecommendedAndRecommender(Member reccommended, Member reccommender);
}
