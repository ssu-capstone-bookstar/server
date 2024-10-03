package book.book.review.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "review_like")
public class ReviewLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reviewId")
    private Review review;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}