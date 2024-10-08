package book.book.collection.entity;

import book.book.book.entity.Book;
import book.book.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "book_collection_book")
@Getter
public class BookCollectionBook extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "collection_id", nullable = false)
    private BookCollection bookCollection;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

}

