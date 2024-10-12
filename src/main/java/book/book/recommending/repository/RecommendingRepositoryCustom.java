package book.book.recommending.repository;

import book.book.recommending.entity.RecommendingBook;

import java.util.List;

public interface RecommendingRepositoryCustom {

    List<RecommendingBook> findBooksOnRecommended(Long recommendedId, Long cursorId, Integer pageSize);
}
