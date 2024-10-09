package book.book.recommending.dto;

import book.book.collection.dto.MinmumBookInfoRequest;
import lombok.Data;

import java.util.List;

@Data
public class SaveRecommedingRequest {

    private List<MinmumBookInfoRequest> minmumBookInfoRequests;

}
