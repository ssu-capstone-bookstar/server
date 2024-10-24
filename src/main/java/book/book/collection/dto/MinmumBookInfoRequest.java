package book.book.collection.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record MinmumBookInfoRequest(
        String title,
        String author,
        @NotNull
        String isbn13,
        LocalDateTime publishedDate,
        String bookCoverImage
) {
}
