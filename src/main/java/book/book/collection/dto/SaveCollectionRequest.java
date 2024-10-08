package book.book.collection.dto;

import jakarta.validation.constraints.Max;
import lombok.Data;

import java.util.List;

@Data
public class SaveCollectionRequest {

    private String name;
    @Max(200)
    private String description;
    private List<BookInfoRequest> bookInfos;

}
