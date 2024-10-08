package book.book.collection.service;

import book.book.collection.dto.BookCollectionResponse;
import book.book.collection.dto.SaveBookCollectionRequest;
import book.book.collection.dto.UpdateBookCollectionRequest;
import book.book.collection.entity.BookCollection;
import book.book.collection.repository.BookCollectionRepository;
import book.book.common.CustomException;
import book.book.common.ResponseForm;
import book.book.common.ResultCode;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BookCollectionService {

    private final MemberRepository memberRepository;
    private final BookCollectionRepository bookCollectionRepository;

    /**
     * 이름이 겹치면 안되는 비지니스 요구사항 때문에 저장과 업데이트를 분리했습니다
     */
    @Transactional
    public void saveBookCollection(Long memberId, SaveBookCollectionRequest rq) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);

        validateSameNameBookCollection(member, rq.getName());

        BookCollection bookCollection = new BookCollection(member, rq.getName(), rq.getDescription());
        bookCollectionRepository.save(bookCollection);
    }

    @Transactional
    public BookCollectionResponse updateBookCollection(Long memberId, Long bookcollectionId, UpdateBookCollectionRequest rq) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        BookCollection bookCollection = bookCollectionRepository.findByIdOrElseThrow(bookcollectionId);

        validateSameNameBookCollection(member, rq.getName());
        bookCollection.validateOwner(memberId);
        bookCollection.update(rq);
        return BookCollectionResponse.from(bookCollection);
    }

    @Transactional
    public void deleteBookCollection(Long memberId, Long bookcollectionId) {
        BookCollection bookCollection = bookCollectionRepository.findByIdOrElseThrow(bookcollectionId);

        bookCollection.validateOwner(memberId);

        bookCollectionRepository.delete(bookCollection);
    }

    private void validateSameNameBookCollection(Member member, String name) {
        if (bookCollectionRepository.existsByMemberAndName(member, name)) {
            throw new CustomException(ResultCode.BOOKCOLLECTION_ALREADY);
        }
    }
}
