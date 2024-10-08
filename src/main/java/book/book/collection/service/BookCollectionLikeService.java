package book.book.collection.service;

import book.book.collection.entity.BookCollection;
import book.book.collection.entity.BookCollectionLike;
import book.book.collection.repository.BookCollectionLikeRepository;
import book.book.collection.repository.BookCollectionRepository;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class BookCollectionLikeService {
    private final BookCollectionLikeRepository bookCollectionLikeRepository;
    private final MemberRepository memberRepository;
    private final BookCollectionRepository bookCollectionRepository;

    @Transactional
    public void saveBookCollectionLike(Long memberId, Long collectionId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        BookCollection bookCollection = bookCollectionRepository.findByIdOrElseThrow(collectionId);

        bookCollectionLikeRepository.findByMemberAndCollection(member, bookCollection)
                .orElse(bookCollectionLikeRepository.save(
                        new BookCollectionLike(member, bookCollection)));
    }

    @Transactional
    public void deleteBookCollectionLike(Long memberId, Long collectionId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        BookCollection bookCollection = bookCollectionRepository.findByIdOrElseThrow(collectionId);

        bookCollectionLikeRepository.deleteByMemberAndCollection(member, bookCollection);
    }
}
