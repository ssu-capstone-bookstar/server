package book.book.book.service;

import book.book.book.dto.SaveReadingStatusRequest;
import book.book.book.dto.SavedBookCommentRequest;
import book.book.book.entity.Book;
import book.book.book.entity.MemberBook;
import book.book.book.repository.BookRepository;
import book.book.book.repository.MemberBookRepository;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class MemberBookService {

    private final MemberBookRepository memberBookRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;

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

    @Transactional
    public void saveBookComment(Long memberId, Long bookId, SavedBookCommentRequest rq) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Book book = bookRepository.findByIdOrElseThrow(bookId);

        MemberBook memberBook = memberBookRepository.findByMemberAndBook(member, book)
                .orElse(new MemberBook());

        memberBook.updateBookComment(rq.getComment());

        memberBookRepository.save(memberBook);
    }

    @Transactional
    public void deleteBookComment(Long memberId, Long bookId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Book book = bookRepository.findByIdOrElseThrow(bookId);

        MemberBook memberBook = memberBookRepository.findByMemberAndBook(member, book)
                .orElseThrow(() -> new CustomException(ResultCode.MEMBERBOOK_NOT_FOUND));

        memberBook.deleteBookComment();
    }
}
