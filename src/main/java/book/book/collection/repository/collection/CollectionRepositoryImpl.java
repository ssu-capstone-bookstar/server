package book.book.collection.repository.collection;

import book.book.collection.entity.Collection;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import static book.book.collection.entity.QCollection.collection;

import java.util.List;

@RequiredArgsConstructor
public class CollectionRepositoryImpl implements CollectionRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public List<Collection> findMyCollections(Long memberId) {
        return queryFactory.select(collection)
                .from(collection)
                .where(collection.member.id.eq(memberId))
                .fetch();
    }
}
