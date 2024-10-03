package book.book.review.entity;

import book.book.book.entity.Book;
import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "review")
@Getter
public class Review extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    //VO
    @Embedded
    private ReviewComment reviewComment;

    @Embedded
    private Star star;

    private Integer rating;
}
