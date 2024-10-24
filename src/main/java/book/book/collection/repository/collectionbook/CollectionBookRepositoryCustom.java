package book.book.collection.repository.collectionbook;

import book.book.book.sort.SortType;
import book.book.collection.entity.CollectionBook;

import java.util.List;

public interface CollectionBookRepositoryCustom {

    List<CollectionBook> findBooks(Long collcetinoId, SortType sortType, Long cursorId, Integer pageSize);
}
