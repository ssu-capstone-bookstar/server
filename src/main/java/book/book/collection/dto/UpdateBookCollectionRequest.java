package book.book.collection.dto;

import lombok.Data;

@Data
public class UpdateBookCollectionRequest {
    private String name;
    private String description;
}
