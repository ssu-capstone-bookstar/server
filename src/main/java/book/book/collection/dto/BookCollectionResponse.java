package book.book.collection.dto;

import book.book.collection.entity.BookCollection;
import book.book.common.ResponseForm;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookCollectionResponse {

    private Long bookCollectionId;
    private Long memberId;
    private String name;
    private String description;

    public static BookCollectionResponse from(BookCollection bookCollection) {
        return new BookCollectionResponse(bookCollection.getId(),
                bookCollection.getMember().getId(),
                bookCollection.getName(),
                bookCollection.getDescription());
    }
}
