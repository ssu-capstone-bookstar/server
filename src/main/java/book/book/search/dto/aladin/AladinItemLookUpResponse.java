package book.book.search.dto.aladin;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinItemLookUpResponse {
    private String version;
    private String title;
    private String pubDate;
    private String imageUrl;
    private int totalResults;
    private int startIndex;
    private int itemsPerPage;
    private String query;
    private int searchCategoryId;
    private String searchCategoryName;
    private List<Item> item;

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Item {
        private String title;
        private String author;
        private String pubDate;
        private String description;
        private String isbn;
        private String isbn13;
        private int itemId;
        private String cover;
        private int categoryId;
        private String categoryName;
        private String publisher;
        private int customerReviewRank;
        private Bookinfo bookinfo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Bookinfo {
        private int itemPage;
        private String toc;
        private List<Author> authors;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Author {
        private String authorType;
        private int authorid;
        private String desc;  // ex) 지은이  or 옮긴이
        private String name;
    }
}