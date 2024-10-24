package book.book.recommending.dto;

import book.book.collection.dto.MinmumBookInfoRequest;
import lombok.Data;

import java.util.List;

public record SaveRecommedingRequest(
        List<MinmumBookInfoRequest> minmumBookInfoRequests
) {
}
