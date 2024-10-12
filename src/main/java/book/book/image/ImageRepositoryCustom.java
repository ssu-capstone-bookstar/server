package book.book.image;

import java.util.List;

public interface ImageRepositoryCustom {
    List<Image> findAllByCollectionIds(List<Long> domainIds);
    List<Image> findAllByBookIds(List<Long> bookIds);
}
