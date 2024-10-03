package book.book.review.entity;

import book.book.book.entity.MemberBook;
import book.book.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "review")
@Getter
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberBookId")
    private MemberBook memberBook;

    private Integer rating;

    @Embedded
    private ReviewComment reviewComment;

}
