package book.book.book.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MyCommentResponse {

    private Long memberBookId;
    private Long bookId;
    private String bookName;
    private String author;
    private String bookCoverImage;
    private String comment;
    private Float star;

    @QueryProjection
    public MyCommentResponse(Long memberBookId, Long bookId, String bookName, String author, String bookCoverImage, String comment, Float star) {
        this.memberBookId = memberBookId;
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.bookCoverImage = bookCoverImage;
        this.comment = comment;
        this.star = star;
    }
}
