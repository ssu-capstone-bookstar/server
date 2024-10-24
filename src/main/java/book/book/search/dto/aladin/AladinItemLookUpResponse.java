package book.book.search.dto.aladin;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinItemLookUpResponse extends AladinApiCommonResponse {
    private List<LookUpItem> item;  //알라딘 API에서 반환할 때 items가 아니 item라는 이름으로 리스트 반환해줌

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class LookUpItem {
        private String title;
        private String author;
        private String pubDate;
        private String description;
        private String isbn;
        private String isbn13;
        private Integer itemId;
        private String cover;
        private Integer categoryId;
        private String categoryName;
        private String publisher;
        private Integer customerReviewRank;
        private Bookinfo bookinfo;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Bookinfo {
        private Integer itemPage;
        private String toc;
        private List<Author> authors;
    }

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Author {
        private Integer authorid;
        private String name;
        private String authorType;
        private String desc;
    }
}