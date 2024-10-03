package book.book.book.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Embeddable
@Getter
public class ReadCompletionDates {
    private List<LocalDate> readCompletionDates;
}
