package book.book.search.dto.aladin;

import lombok.Data;

@Data
public class AladinSearchRequest {

    private int Start;
    private int MaxResults;
    private String Output; //JS으로

}
