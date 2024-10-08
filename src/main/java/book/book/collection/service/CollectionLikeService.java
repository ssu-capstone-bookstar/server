package book.book.collection.service;

import book.book.collection.entity.Collection;
import book.book.collection.entity.CollectionLike;
import book.book.collection.repository.CollectionLikeRepository;
import book.book.collection.repository.CollectionRepository;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
public class CollectionLikeService {
    private final CollectionLikeRepository collectionLikeRepository;
    private final MemberRepository memberRepository;
    private final CollectionRepository collectionRepository;

    @Transactional
    public void saveCollectionLike(Long memberId, Long collectionId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Collection collection = collectionRepository.findByIdOrElseThrow(collectionId);

        collectionLikeRepository.findByMemberAndCollection(member, collection)
                .orElse(collectionLikeRepository.save(
                        new CollectionLike(member, collection)));
    }

    @Transactional
    public void deleteCollectionLike(Long memberId, Long collectionId) {
        Member member = memberRepository.findByIdOrElseThrow(memberId);
        Collection collection = collectionRepository.findByIdOrElseThrow(collectionId);

        collectionLikeRepository.deleteByMemberAndCollection(member, collection);
    }
}
