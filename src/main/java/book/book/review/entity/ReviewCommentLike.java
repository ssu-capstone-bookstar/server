package book.book.review.entity;

import book.book.common.BaseEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "review_comment_like")
public class ReviewCommentLike extends BaseEntity {
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