package book.book.search.dto.aladin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
public class AladinSearchResponse {
    private String version;
    private Integer tile; //알라딘이 어떤 검색어로 검색했는지
    private Integer totalResults;  //알라딘 검색 결과 개수
    private Integer startIndex;
    private Integer itemsPerPage;
    private String query;
    private Integer searchCategoryId;
    private String searchCategoryName;
    private List<Item> item;

    @Data
    static class Item {
        private String title;
        private String author;
        private String pubDate;
        private String description; //요약
        private Integer isbn;
        private Integer isbn13;
        private Integer itemId;
        private String cover;
        private Integer categoryId;
        private String categoryName;
        private String publisher;
        private SubInfo subInfo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SubInfo {
        private Integer itemPage;
    }
}
