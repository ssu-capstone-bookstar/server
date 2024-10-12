package book.book.collection.repository.collectionbook;

import book.book.collection.entity.CollectionBook;

import java.util.List;

public interface CollectionBookCustom {

     List<CollectionBook> findBooks(Long collcetinoId, Long cursorId, Integer pageSize);
}
