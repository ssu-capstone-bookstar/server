package book.book.book.service;

import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.entity.Book;
import book.book.book.entity.Rating;
import book.book.book.repository.BookRepository;
import book.book.book.repository.RatingRepository;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

    @Transactional
    public void saveReadingStatus(Long memberId, Long bookId, SaveReadingStatusRequest rq) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Book book = bookRepository.findByIdOrElseThrow(bookId);

        Rating rating = ratingRepository.findByMemberAndBook(member, book)
                .orElse(new Rating());

        rating.updateReadingStatus(rq.getReadingStatus(), rq.getRating());

        ratingRepository.save(rating);
    }
}
