package book.book.collection.dto;

import book.book.collection.entity.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CollectionResponse {

    private Long bookCollectionId;
    private String name;
    private String description;
    private List<String> images;
    public static CollectionResponse of(Collection collection, List<String> images) {
        return new CollectionResponse(
                collection.getId(),
                collection.getName(),
                collection.getDescription(),
                images);
    }
}
