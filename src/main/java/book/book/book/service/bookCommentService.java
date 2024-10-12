package book.book.book.service;

import book.book.book.dto.SavedBookCommentRequest;
import book.book.book.entity.Book;
import book.book.book.entity.BookCommentLike;
import book.book.book.entity.MemberBook;
import book.book.book.repository.BookCommentLikeRepository;
import book.book.book.repository.BookRepository;
import book.book.book.repository.MemberBookRepository;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class bookCommentService {

    private final MemberRepository memberRepository;
    private final MemberBookRepository memberBookRepository;
    private final BookCommentLikeRepository bookCommentLikeRepository;
    private final BookRepository bookRepository;

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
    @Transactional
    public void saveBookCommentLike(Long memberId, Long memberBookId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        MemberBook memberBook = memberBookRepository.findByIdOrElseThrow(memberBookId);

        bookCommentLikeRepository.findByMemberAndMemberBook(member, memberBook)
                .orElse(bookCommentLikeRepository.save(
                        new BookCommentLike(member, memberBook)));
    }

    @Transactional
    public void deleteBookCommentLike(Long memberId, Long memberBookId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        MemberBook memberBook = memberBookRepository.findByIdOrElseThrow(memberBookId);

        bookCommentLikeRepository.deleteByMemberAndMemberBook(member, memberBook);
    }
}
