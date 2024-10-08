package book.book.image;

import java.util.List;

public interface ImageRepositoryCustom {
    List<Image> findAllByBookCollectionIds(List<Long> domainIds);
}
