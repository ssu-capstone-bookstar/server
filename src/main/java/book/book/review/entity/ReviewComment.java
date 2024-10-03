package book.book.review.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable
@Getter
public class ReviewComment {


    @Column(length = 255)
    private String content;

}
