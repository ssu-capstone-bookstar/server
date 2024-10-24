package book.book.collection.service;

import book.book.collection.dto.MinmumBookInfoRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CollectionImageService {

    public List<String> getBookCollectionThumbnail(List<MinmumBookInfoRequest> minmumBookInfoRequests) {
        return minmumBookInfoRequests.stream()
                .map(MinmumBookInfoRequest::bookCoverImage)
                .filter(Objects::nonNull)
                .limit(4)
                .toList();
    }
}
