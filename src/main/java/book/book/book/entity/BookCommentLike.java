package book.book.book.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_comment_like")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookCommentLike extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "member_book_id", nullable = false)
    private MemberBook memberBook;

    public BookCommentLike(Member member, MemberBook memberBook) {
        this.member = member;
        this.memberBook = memberBook;
    }
}
