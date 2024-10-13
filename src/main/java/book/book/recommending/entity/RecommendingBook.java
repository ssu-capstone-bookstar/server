package book.book.recommending.entity;

import book.book.book.entity.Book;
import book.book.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RecommendingBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Recommending recommending;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;

    public RecommendingBook(Recommending recommending, Book book) {
        this.recommending = recommending;
        this.book = book;
    }
}