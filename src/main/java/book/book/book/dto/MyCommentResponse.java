package book.book.book.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MyCommentResponse {

    private final Long memberBookId;
    private final Long bookId;
    private final String bookTitle;
    private final String bookCoverImage;
    private final String comment;
    private final Float star;
    private String author;

    @QueryProjection
    public MyCommentResponse(Long memberBookId, Long bookId, String bookTitle, String author, String bookCoverImage, String comment, Float star) {
        this.memberBookId = memberBookId;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.author = author;
        this.bookCoverImage = bookCoverImage;
        this.comment = comment;
        this.star = star;
    }
}
