package book.book.collection.dto;

import book.book.collection.entity.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * BookCollectionResponses가 BooCollctions이랑 Images랑 넘겨받아서 매핑시키는 게 맞을까?
 */
@Data
@AllArgsConstructor
public class CollectionResponses {
    private List<CollectionResponse> collectionRespons;

    public static CollectionResponses of(List<Collection> collections,
                                         Map<Long, List<String>> imagesMap) {

        List<CollectionResponse> collectionRespons = new ArrayList<>();

        for (Collection collection : collections) {
            List<String> imagePaths = imagesMap.getOrDefault(collection.getId(), Collections.emptyList());

            CollectionResponse response = CollectionResponse.of(collection, imagePaths);

            collectionRespons.add(response);
        }

        return new CollectionResponses(collectionRespons);
    }
}
