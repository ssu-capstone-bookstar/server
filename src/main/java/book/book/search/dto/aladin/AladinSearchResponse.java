package book.book.search.dto.aladin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AladinSearchResponse extends AladinApiCommonResponse {
    private List<SearchItem> item; //알라딘 API에서 반환할 때 items가 아니 item라는 이름으로 리스트 반환해줌

    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class SearchItem {
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
    }
}
