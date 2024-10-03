package book.book.book.entity;

import book.book.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "book")
@Getter
public class Book extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    @Column(length = 100)
    private String title;

    @Column(length = 30)
    private String author;

    @Column(length = 20)
    private String isbn;

    @Column(length = 10)
    private String category;

    @Column(length = 100)
    private String description;

    private LocalDateTime publishedDate;

    @Column(length = 100)
    private String bookCoverImage;

}
