package book.book.collection.repository.collectionbook;

import book.book.collection.entity.Collection;
import book.book.collection.entity.CollectionBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionBookRepository extends JpaRepository<CollectionBook, Long>, CollectionBookRepositoryCustom {
    void deleteAllByCollection(Collection collection);
}
