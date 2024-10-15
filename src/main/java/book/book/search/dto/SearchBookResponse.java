package book.book.search.dto;

import book.book.search.dto.aladin.AladinSearchResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SearchBookResponse {

    private String title;
    private String bookCover;
    private String pubDate;
    private String author;

    public static SearchBookResponse of (AladinSearchResponse.SearchItem items) {
        return new SearchBookResponse(items.getTitle(), items.getCover(), items.getPubDate(), items.getAuthor());
    }
}
