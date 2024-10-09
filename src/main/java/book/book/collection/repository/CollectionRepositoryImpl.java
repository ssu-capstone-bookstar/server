package book.book.collection.repository;

import book.book.collection.entity.Collection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static book.book.collection.entity.QBookCollection.bookCollection;
import static book.book.collection.entity.QBookCollectionLike.bookCollectionLike;

@RequiredArgsConstructor
public class CollectionRepositoryImpl implements CollectionRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Collection> findLikedBookCollections(Long memberId) {
        return queryFactory
                .selectFrom(bookCollection)
                .join(bookCollectionLike).on(bookCollectionLike.bookCollection.id.eq(bookCollection.id))
                .where(bookCollectionLike.member.id.eq(memberId))
                .fetch();
    }
}
