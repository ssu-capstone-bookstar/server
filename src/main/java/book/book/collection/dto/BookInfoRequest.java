package book.book.collection.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookInfoRequest {

    private String title;
    private String author;
    private String isbn;
    private LocalDateTime publishedDate;
    private String bookCoverImage;
}
