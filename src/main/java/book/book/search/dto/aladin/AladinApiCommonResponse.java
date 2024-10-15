package book.book.search.dto.aladin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinApiCommonResponse {
    private String version;
    private String title;
    private Integer totalResults;
    private Integer startIndex;
    private Integer itemsPerPage;
    private String query;
    private Integer searchCategoryId;
    private String searchCategoryName;
}