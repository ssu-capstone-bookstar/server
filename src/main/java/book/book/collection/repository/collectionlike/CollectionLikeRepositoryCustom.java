package book.book.collection.repository.collectionlike;

import book.book.collection.entity.Collection;

import java.util.List;

public interface CollectionLikeRepositoryCustom {
    List<Collection> findLikedCollections(Long memberId);
}
