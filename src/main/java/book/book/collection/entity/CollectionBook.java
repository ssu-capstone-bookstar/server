package book.book.collection.entity;

import book.book.book.entity.Book;
import book.book.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "collection_book")
@Getter
public class CollectionBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}

