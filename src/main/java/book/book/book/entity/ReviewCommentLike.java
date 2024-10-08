package book.book.book.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "review_commnet_like")
@Getter
public class ReviewCommentLike extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ratingId")
    private MemberBook memberBook;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;
}