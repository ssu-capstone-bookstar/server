package book.book.recommending;

import book.book.book.entity.Book;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class RecommendingBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Recommending recommending;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Book book;
}