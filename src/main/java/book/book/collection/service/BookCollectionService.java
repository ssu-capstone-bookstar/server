package book.book.collection.service;

import book.book.collection.dto.SaveBookCollectionRequest;
import book.book.collection.entity.BookCollection;
import book.book.collection.repository.BookCollectionRepository;
import book.book.common.CustomException;
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

        // 같은 이름의 컬렉션이 이미 존재하는지 확인
        if (bookCollectionRepository.existsByMemberAndName(member, rq.getName())) {
            throw new CustomException(ResultCode.BOOKCOLLECTION_ALREADY);
        }

        BookCollection bookCollection = new BookCollection(member, rq.getName(), rq.getDescription());
        bookCollectionRepository.save(bookCollection);
    }

    @Transactional
    public void deleteBookCollection(Long memberId, Long bookcollectionId) {
        BookCollection bookCollection = bookCollectionRepository.findByIdOrElseThrow(bookcollectionId);

        bookCollection.validateOwner(memberId);

        bookCollectionRepository.delete(bookCollection);
    }
}
