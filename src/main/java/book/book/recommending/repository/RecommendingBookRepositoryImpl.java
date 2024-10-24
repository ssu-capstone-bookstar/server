package book.book.recommending.repository;

import book.book.book.sort.SortType;
import book.book.recommending.entity.RecommendingBook;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static book.book.book.entity.QBook.book;
import static book.book.recommending.entity.QRecommendingBook.recommendingBook;

@RequiredArgsConstructor
public class RecommendingBookRepositoryImpl implements RecommendingBookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<RecommendingBook> findBooksOnRecommended(
            Long recommendedId,
            SortType sortType,
            Long cursorId,
            Integer pageSize) {

        BooleanExpression whereClause = recommendingBook.recommending.recommended.id.eq(recommendedId);

        if (cursorId != null) {
            whereClause = whereClause.and(recommendingBook.id.lt(cursorId));
        }

        return queryFactory
                .selectFrom(recommendingBook)
                .innerJoin(recommendingBook.book, book).fetchJoin()
                .where(whereClause)
                .orderBy(sortType.getOrderExpression())
                .limit(pageSize)
                .fetch();
    }
}
