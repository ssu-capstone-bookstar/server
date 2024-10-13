package book.book.book.repository;

import book.book.book.dto.MyCommentResponse;
import book.book.book.dto.QMyCommentResponse;
import book.book.book.entity.MemberBook;
import book.book.book.entity.ReadingStatus;
import book.book.book.sort.SortType;
import book.book.image.ImageCategory;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static book.book.book.entity.QBook.book;
import static book.book.book.entity.QMemberBook.memberBook;
import static book.book.image.QImage.image;

@RequiredArgsConstructor
public class MemberBookRepositoryImpl implements MemberBookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * memberBookId로 커서를 써야 하나 BookId로 써야 하나
     */
    @Override
    public List<MemberBook> findBookByReadingStatus(
            Long memberId,
            ReadingStatus readingStatus,
            SortType sortType,
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
                .orderBy(sortType.getOrderExpression())
                .limit(pageSize)
                .fetch();
    }

    @Override
    public List<MyCommentResponse> findCommentsByMemberId(
            Long memberId,
            SortType sortType,
            Long cursorId,
            Integer pageSize) {


        BooleanExpression whereClause = memberBook.member.id.eq(memberId);

        if (cursorId != null) {
            whereClause = whereClause.and(memberBook.id.lt(cursorId));
        }


        return queryFactory
                .select(new QMyCommentResponse(
                        memberBook.id,
                        book.id,
                        book.title,
                        book.author,
                        image.path,
                        memberBook.bookComment.content,
                        memberBook.star.count
                ))
                .from(memberBook)
                .innerJoin(book).on(memberBook.book.id.eq(book.id))
                .leftJoin(image).on(book.id.eq(image.domainId)
                        .and(image.imageCategory.eq(ImageCategory.BOOK)))
                .where(whereClause)
                .orderBy(sortType.getOrderExpression())
                .limit(pageSize)
                .fetch();
    }
}
