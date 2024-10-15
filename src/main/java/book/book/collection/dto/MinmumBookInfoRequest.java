package book.book.collection.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MinmumBookInfoRequest {

    private String title;
    private String author;
    private String isbn13;
    private LocalDateTime publishedDate;
    private String bookCoverImage;
}
