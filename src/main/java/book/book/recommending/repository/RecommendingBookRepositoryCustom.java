package book.book.recommending.repository;

import book.book.book.sort.SortType;
import book.book.recommending.entity.RecommendingBook;

import java.util.List;

public interface RecommendingBookRepositoryCustom {

    List<RecommendingBook> findBooksOnRecommended(Long recommendedId, SortType sortType, Long cursorId, Integer pageSize);
}
