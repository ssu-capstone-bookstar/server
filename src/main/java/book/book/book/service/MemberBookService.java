package book.book.book.service;

import book.book.book.dto.BookThumbnailResponse;
import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.entity.Book;
import book.book.book.entity.MemberBook;
import book.book.book.entity.ReadingStatus;
import book.book.book.repository.BookRepository;
import book.book.book.repository.MemberBookRepository;
import book.book.book.sort.SortType;
import book.book.common.CursorPageResponse;
import book.book.image.ImageService;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberBookService {

    private static final Integer PAGE_SIZE = 12;
    private final MemberBookRepository memberBookRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final ImageService imageService;

    @Transactional
    public void saveReadingStatus(Long memberId, Long bookId, SaveReadingStatusRequest rq) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Book book = bookRepository.findByIdOrElseThrow(bookId);

        MemberBook memberBook = memberBookRepository.findByMemberAndBook(member, book)
                .orElse(new MemberBook());

        memberBook.updateReadingStatus(rq.getReadingStatus(), rq.getStar());

        memberBookRepository.save(memberBook);
    }

    @Transactional
    public void deleteReadingStatus(Long memberId, Long bookId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Book book = bookRepository.findByIdOrElseThrow(bookId);

        memberBookRepository.deleteByMemberAndBook(member, book);
    }

    @Transactional(readOnly = true)
    public CursorPageResponse<BookThumbnailResponse> getBookByReadingStatus(
            Long memberId,
            ReadingStatus readingStatus,
            SortType sort,
            Long cursorId) {

        List<MemberBook> memberBooks = memberBookRepository.findBookByReadingStatus(memberId, readingStatus, sort, cursorId, PAGE_SIZE);
        List<Long> bookIds = getBookIds(memberBooks);

        Map<Long, String> imagesMap = imageService.getAllByBookIds(bookIds);

        List<BookThumbnailResponse> bookThumbnailResponses = MappingMemberBookAndImage.mapping(memberBooks, imagesMap);
        return CursorPageResponse.of(bookThumbnailResponses, PAGE_SIZE, BookThumbnailResponse::getMemberBookId);

    }

    private List<Long> getBookIds(List<MemberBook> memberBooks) {
        return memberBooks.stream()
                .map(memberBook -> memberBook.getBook().getId())
                .toList();
    }
}
