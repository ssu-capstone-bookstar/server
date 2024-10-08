package book.book.collection.repository;

import book.book.collection.entity.Collection;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CollectionRepository extends JpaRepository<Collection, Long> {

    Optional<Collection> findById(Long collectionId);

    default Collection findByIdOrElseThrow(Long id) {
        return findById(id)
                .orElseThrow(() -> new CustomException(ResultCode.COLLECTION_NOT_FOUND));
    }
}
