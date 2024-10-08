package book.book.book.entity;

import book.book.common.BaseTimeEntity;
import book.book.common.CustomException;
import book.book.common.ResultCode;
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

    @Embedded
    private BookComment bookComment;

    @Embedded
    private Star star; // 이것만이 Readed로 바꿀 수 있음

    @Enumerated(value = EnumType.STRING)
    private ReadingStatus readingStatus;

    @Embedded
    private ReadCompletionDates readCompletionDate;


    public void updateReadingStatus(ReadingStatus status, Float star) {

        if (status == ReadingStatus.READED) {
            validateRatingForReadedStatus(star);
            this.star = new Star(star);
        }

        this.readingStatus = status;
    }

    /**
     * star의 책임일까 rating의 책임일까
     */
    private void validateRatingForReadedStatus(Float rating) {
        if (rating == null || rating <= 0) {
            throw new CustomException(ResultCode.READING_STATUS_RATING_REQUIRED);
        }
    }

    public void deleteBookComment() {
        String BLANK_STRING = "";
        updateBookComment(BLANK_STRING);
    }

    public void updateBookComment(String comment) {
        this.bookComment = new BookComment(comment);
    }


}
