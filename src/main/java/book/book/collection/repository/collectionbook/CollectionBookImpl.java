package book.book.collection.repository.collectionbook;

import book.book.book.sort.SortType;
import book.book.collection.entity.CollectionBook;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static book.book.book.entity.QBook.book;
import static book.book.collection.entity.QCollectionBook.collectionBook;

@RequiredArgsConstructor
public class CollectionBookImpl implements CollectionBookCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public List<CollectionBook> findBooks(
            Long collcetinoId,
            Long cursorId,
            Integer pageSize) {

        BooleanExpression whereClause = collectionBook.collection.id.eq(collcetinoId);

        if (cursorId != null) {
            whereClause = whereClause.and(collectionBook.id.lt(cursorId));
        }

        return queryFactory
                .selectFrom(collectionBook)
                .innerJoin(collectionBook.book, book).fetchJoin()
                .where(whereClause)
                .orderBy(SortType.RECENT.getOrderExpression())
                .limit(pageSize)
                .fetch();
    }
}
