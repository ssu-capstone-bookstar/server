package book.book.book.dto;

import book.book.book.entity.ReadingStatus;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;



@Data
public class SaveReadingStatusRequest {

    @NotNull
    private ReadingStatus readingStatus;

    @Min(0)
    @Max(5)
    private Float rating;

}
