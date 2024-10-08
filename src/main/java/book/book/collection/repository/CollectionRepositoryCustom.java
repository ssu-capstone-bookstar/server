package book.book.collection.repository;

import book.book.collection.entity.Collection;

import java.util.List;

public interface CollectionRepositoryCustom {

    List<Collection> findLikedBookCollection(Long memberId);
}
