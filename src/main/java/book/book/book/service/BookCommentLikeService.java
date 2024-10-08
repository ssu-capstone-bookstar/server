package book.book.book.service;

import book.book.book.entity.BookCommentLike;
import book.book.book.entity.MemberBook;
import book.book.book.repository.BookCommentLikeRepository;
import book.book.book.repository.MemberBookRepository;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BookCommentLikeService {

    private final MemberRepository memberRepository;
    private final MemberBookRepository memberBookRepository;
    private final BookCommentLikeRepository bookCommentLikeRepository;

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
