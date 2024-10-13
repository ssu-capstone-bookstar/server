package book.book.recommending.dto;

import book.book.recommending.entity.RecommendingBook;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecommendingBookResponse {

    private Long recommendingBookId;
    private Long bookId;
    private String title;
    private String bookCoverImage;
    private Float averageStar;

    public static RecommendingBookResponse of(RecommendingBook recommendingBook, String imagePath) {
        return new RecommendingBookResponse(
                recommendingBook.getId(),
                recommendingBook.getId(),
                recommendingBook.getBook().getTitle(),
                imagePath,
                5F //TODO
        );
    }
}
