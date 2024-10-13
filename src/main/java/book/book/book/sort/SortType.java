package book.book.book.sort;


import book.book.book.entity.QBook;
import book.book.book.entity.QMemberBook;
import book.book.collection.entity.QCollectionBook;
import book.book.recommending.entity.QRecommendingBook;
import com.querydsl.core.types.OrderSpecifier;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Supplier;

@Getter
@RequiredArgsConstructor
public enum SortType {
    MEMEBER_BOOK_RECENT("내 서제에 최근에 담은 순", () -> List.of(QMemberBook.memberBook.updatedDate.desc())),
    COLLECTION_BOOK_RECENT("내 컬렉션에 최근에 담은 순", () -> List.of(QCollectionBook.collectionBook.createdDate.desc())),
    RECOMMENDING_BOOK_RECENT("내 추천함에 최근에 담은 순", () -> List.of(QRecommendingBook.recommendingBook.createdDate.desc())),
//    AVEARAGE_RATING("평균 별점 높은 순", () -> List.of(QBoard.board.expectedRating.desc(), QBoard.board.id.desc())),
    MY_RATING("내 별점 높은 순", () -> List.of(QMemberBook.memberBook.star.count.desc(), QBook.book.id.desc())),
    KOREAN_ALPHABETICAL("가나다 순", () -> List.of(QBook.book.title.asc(), QBook.book.id.desc())),
    NEWEST("신작 순", () -> List.of(QBook.book.publishedDate.desc(), QBook.book.id.desc()));
//    MOST_REVIEWED("평가 많은 순", () -> List.of(QBoardStatistic.boardStatistic.boardReviewCount.desc(), QBoard.board.id.desc()))

    private final String description;
    //supplier에 대해서 공부
    private final Supplier<List<OrderSpecifier<?>>> setOrder;

    public OrderSpecifier<?>[] getOrderExpression() {
        return setOrder.get().toArray(new OrderSpecifier[0]);
    }
}
