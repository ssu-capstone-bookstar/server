package book.book.book.dto;

import book.book.book.entity.MemberBook;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberBookResponse {
    private Long memberBookId;
    private String title;
    private String bookCoverImage;
    private Float averageStar;
    private Float myStar;

    public static MemberBookResponse of(MemberBook memberBook, String imagePath) {
        return new MemberBookResponse(
                memberBook.getId(),
                memberBook.getBook().getTitle(),
                imagePath,
                5F , //TODO
                memberBook.getStar().getCount()
        );
    }
}
