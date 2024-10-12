package book.book.book.entity;

import book.book.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * ISBN (유일값) 기준으로 책을 찾습니다
 */
@Entity
@Table(name = "book")
@Getter
public class Book extends BaseTimeEntity {  //속성 추가 예정
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String author;

    @Column(length = 16)
    private String isbn;

    @Enumerated(value = EnumType.STRING)
    private BookCategory bookCategory;

    @Column(length = 100)
    private String description;

    private LocalDateTime publishedDate;

    private String bookCoverImage;

}
