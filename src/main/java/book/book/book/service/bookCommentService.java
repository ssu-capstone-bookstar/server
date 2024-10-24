package book.book.book.service;

import book.book.book.dto.MyCommentResponse;
import book.book.book.dto.SavedBookCommentRequest;
import book.book.book.entity.Book;
import book.book.book.entity.BookCommentLike;
import book.book.book.entity.MemberBook;
import book.book.book.repository.BookCommentLikeRepository;
import book.book.book.repository.BookRepository;
import book.book.book.repository.MemberBookRepository;
import book.book.book.sort.SortType;
import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.common.response.CursorPageResponse;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class bookCommentService {

    private static final int PAGE_SIZE = 10;
    private final MemberRepository memberRepository;
    private final MemberBookRepository memberBookRepository;
    private final BookCommentLikeRepository bookCommentLikeRepository;
    private final BookRepository bookRepository;

    /**
     * Response 두개 를 더해 Response 하나를 만들 때 CusrorPageResponse를 어떻게 만들까?
     * <p>
     * 1. 최상단 단일 속성 + 하위 리스트 속성  - 문제점: CursorPageResponse에 list.get() 메소드가 사용이 안됨
     * 즉, CursorPageResponse를 사용하지 못함
     * <p>
     * 결론 - API 호출 2회로 나눔
     * 1.맨 처음 페이지 열 떄 맴버 닉네임과 이미지 가져오는 API 단 일 회 호출하고
     * 2. 그 다음 커서기반으로 평가 호출
     */
    @Transactional(readOnly = true)
    public CursorPageResponse<MyCommentResponse> getMyComments(Long memberId, SortType sortType, Long cursorId) {
        List<MyCommentResponse> myCommentResponses = memberBookRepository.findCommentsByMemberId(
                memberId,
                sortType,
                cursorId,
                PAGE_SIZE);

        return CursorPageResponse.of(myCommentResponses, PAGE_SIZE, MyCommentResponse::getMemberBookId);
    }

    @Transactional
    public void saveBookComment(Long memberId, Long bookId, SavedBookCommentRequest rq) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Book book = bookRepository.findByIdOrElseThrow(bookId);

        MemberBook memberBook = memberBookRepository.findByMemberAndBook(member, book)
                .orElse(new MemberBook());

        memberBook.updateBookComment(rq.comment());

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
                .orElseGet(() -> bookCommentLikeRepository.save(
                        new BookCommentLike(member, memberBook)));
    }

    @Transactional
    public void deleteBookCommentLike(Long memberId, Long memberBookId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        MemberBook memberBook = memberBookRepository.findByIdOrElseThrow(memberBookId);

        bookCommentLikeRepository.deleteByMemberAndMemberBook(member, memberBook);
    }

}
