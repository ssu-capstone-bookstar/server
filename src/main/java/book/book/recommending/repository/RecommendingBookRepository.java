package book.book.recommending.repository;

import book.book.recommending.entity.RecommendingBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecommendingBookRepository extends JpaRepository<RecommendingBook, Long>, RecommendingBookRepositoryCustom {
}
