package book.book.book.entity;

import book.book.common.BaseTimeEntity;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.search.dto.aladin.AladinSearchResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * ISBN (유일값) 기준으로 책을 찾습니다
 */
@Entity
@Table(name = "book")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int aladingBookId;

    @Column
    private String title;

    @OneToMany(mappedBy = "book")
    private List<BookAuthor> bookAuthors = new ArrayList<>();


    @Column(length = 10)
    private String isbn;

    @Column(length = 13)
    private String isbn13;

    @Enumerated(value = EnumType.STRING)
    private BookCategory bookCategory;

    @Column
    private String description;
    private String publisher;
    private LocalDate publishedDate;
    private Integer page;
    private Integer toc;


    public static Book fromAladinSearchResponse(AladinSearchResponse.SearchItem item) {
        return Book.builder()
                .aladingBookId(item.getItemId())
                .title(item.getTitle())
                .isbn(item.getIsbn())
                .isbn13(item.getIsbn13())
                .bookCategory(BookCategory.fromAladinCategory(item.getCategoryName())) //TODO
                .description(item.getDescription()) // TODO 요약말고 진짜 저장할 때
                .publisher(item.getPublisher())
                .publishedDate(parsedDate(item.getPubDate()))
                .build();
    }

    private static LocalDate parsedDate(String dateString) {
        DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            return LocalDate.parse(dateString, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new CustomException(ResultCode.PARE_DATE_STRING_ERROR);
        }
    }

}