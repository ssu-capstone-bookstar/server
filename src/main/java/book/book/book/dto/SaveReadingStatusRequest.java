package book.book.book.dto;

import book.book.book.entity.ReadingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record SaveReadingStatusRequest(
        @NotNull
        ReadingStatus readingStatus,
        @Min(0)
        @Max(5)
        Float star
) {
}
