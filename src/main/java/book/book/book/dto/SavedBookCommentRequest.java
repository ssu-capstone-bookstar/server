package book.book.book.dto;

import jakarta.validation.constraints.Size;

public record SavedBookCommentRequest(
        @Size(max = 1024)
        String comment
) {
}
