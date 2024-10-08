package book.book.collection.service;

import book.book.collection.dto.BookInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CollectionImageService {

    public List<String> getBookCollectionThumbnail(List<BookInfoRequest> bookInfoRequests) {
        return bookInfoRequests.stream()
                .map(BookInfoRequest::getBookCoverImage)
                .filter(Objects::nonNull)
                .limit(4)
                .toList();
    }
}
