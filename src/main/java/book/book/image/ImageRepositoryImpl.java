package book.book.image;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static book.book.collection.entity.QCollectionBook.collectionBook;
import static book.book.image.QImage.image;
import static book.book.book.entity.QBook.book;

@RequiredArgsConstructor
public class ImageRepositoryImpl implements ImageRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    /**
     * 각 BookCollecion마다 Book Image 4개만 쓸 건데
     * BookCollectionBook에 해당하는 이미지 다 데이터베이스에서 가져오는 건 낭비잖아
     * <p>
     * 1. 북컬렉션마다 쿼리 호출
     * 2. 한번에 데이터 다 가져와서 분리
     * 어느 것이 성능 더 좋을까
     * 그룹당 결과 수를 제한하려면(예: 데이터베이스에서 직접 bookCollectionBook당 상위 4개 이미지 가져오기) 일반적으로 SQL의 ROW_NUMBER()
     * Java에서 사후 처리를 적용합니다(대규모 데이터 세트의 경우 효율성이 떨어짐).
     */
    //TODO 쿼리점검
    @Override
    public List<Image> findAllByCollectionIds(List<Long> collectionIds) {
        return queryFactory
                .selectFrom(image)
                .join(book).on(image.domainId.eq(book.id))
                .join(collectionBook).on(book.id.eq(collectionBook.book.id))
                .where(collectionBook.collection.id.in(collectionIds))
                .orderBy(image.domainId.asc(), image.createdDate.asc())
                .fetch();
    }
}
