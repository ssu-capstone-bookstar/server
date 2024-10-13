package book.book.collection.dto;

import book.book.collection.entity.CollectionBook;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CollectionBookResponse {
    private Long collectionBookId;
    private Long bookId;
    private String title;
    private String bookCoverImage;
    private Float averageStar;

    public static CollectionBookResponse of(CollectionBook collectionBook, String imagePath) {
        return new CollectionBookResponse(
                collectionBook.getId(),
                collectionBook.getBook().getId(),
                collectionBook.getBook().getTitle(),
                imagePath,
                5F //TODO
        );
    }
}
