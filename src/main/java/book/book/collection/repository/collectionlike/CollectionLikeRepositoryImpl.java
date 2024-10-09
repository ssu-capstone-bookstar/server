package book.book.collection.repository.collectionlike;

import book.book.collection.entity.Collection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static book.book.collection.entity.QCollection.collection;
import static book.book.collection.entity.QCollectionLike.collectionLike;

@RequiredArgsConstructor
public class CollectionLikeRepositoryImpl implements CollectionLikeRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Collection> findLikedCollections(Long memberId) {
        return queryFactory
                .selectFrom(collection)
                .join(collectionLike).on(collectionLike.collection.id.eq(collection.id))
                .where(collectionLike.member.id.eq(memberId))
                .fetch();
    }
}
