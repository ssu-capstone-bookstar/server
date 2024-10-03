package book.book.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import lombok.Getter;

@Embeddable
@Getter
public class Star {

    @Column
    @Max(5)
    @Digits(integer = 1, fraction = 1)
    private Float count;
}
