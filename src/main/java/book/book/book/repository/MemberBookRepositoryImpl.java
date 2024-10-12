package book.book.book.repository;

import book.book.book.entity.MemberBook;
import book.book.book.entity.ReadingStatus;
import book.book.book.sort.SortType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static book.book.book.entity.QMemberBook.memberBook;
import static book.book.book.entity.QBook.book;

@RequiredArgsConstructor
public class MemberBookRepositoryImpl implements MemberBookRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    /**
     * memberBookId로 커서를 써야 하나 BookId로 써야 하나
     */
    @Override
    public List<MemberBook> findBookByReadingStatus(
            Long memberId,
            ReadingStatus readingStatus,
            SortType sort,
            Long cursorId,
            Integer pageSize) {

        BooleanExpression whereClause = memberBook.member.id.eq(memberId)
                .and(memberBook.readingStatus.eq(readingStatus));

        if (cursorId != null) {
            whereClause = whereClause.and(memberBook.id.lt(cursorId));
        }

        return queryFactory
                .selectFrom(memberBook)
                .innerJoin(memberBook.book, book).fetchJoin()
                .where(whereClause)
                .orderBy(sort.getOrderExpression())
                .limit(pageSize)
                .fetch();
    }
}
