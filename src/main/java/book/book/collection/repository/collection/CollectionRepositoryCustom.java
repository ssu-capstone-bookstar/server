package book.book.collection.repository.collection;

import book.book.collection.entity.Collection;

import java.util.List;

public interface CollectionRepositoryCustom {

    List<Collection> findMyCollections(Long memberId);
}
