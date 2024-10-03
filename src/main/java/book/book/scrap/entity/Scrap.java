package book.book.scrap.entity;

import book.book.book.entity.Book;
import book.book.common.BaseTimeEntity;
import book.book.member.entity.Member;
import jakarta.persistence.*;

@Entity
@Table(name = "scrap")
public class Scrap extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "bookId")
    private Book book;
}