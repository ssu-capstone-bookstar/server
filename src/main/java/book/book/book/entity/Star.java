package book.book.book.entity;

import book.book.common.CustomException;
import book.book.common.ResultCode;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Star {

    @Column
    @Max(5)
    @Digits(integer = 2, fraction = 1) //TODO
    private Float count;

}
