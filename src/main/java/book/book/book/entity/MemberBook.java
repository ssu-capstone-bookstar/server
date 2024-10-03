package book.book.book.entity;

import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "member_book")
@Getter
public class MemberBook extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @Enumerated(value = EnumType.STRING)
    private ReadingStatus readingStatus;

    @Embedded
    private ReadCompletionDates readCompletionDate;

}
