package book.book.book.entity;

import book.book.common.BaseEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "member_book")
@Getter
public class MemberBook extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;

    @Column(length = 10)
    private String status;

    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
