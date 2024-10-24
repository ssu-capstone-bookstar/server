package book.book.collection.dto;

import jakarta.validation.constraints.Max;

import java.util.List;

public record SaveCollectionRequest(
        String name,
        @Max(200)
        String description,
        List<MinmumBookInfoRequest> bookInfos
) {
}


