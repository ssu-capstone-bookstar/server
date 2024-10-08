package book.book.collection.service;

import book.book.collection.dto.CollectionResponses;
import book.book.collection.entity.Collection;
import book.book.collection.entity.CollectionLike;
import book.book.collection.repository.CollectionLikeRepository;
import book.book.collection.repository.CollectionRepository;
import book.book.image.ImageService;
import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CollectionLikeService {
    private final CollectionLikeRepository collectionLikeRepository;
    private final MemberRepository memberRepository;
    private final CollectionRepository collectionRepository;
    private final ImageService imageService;

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

    /**
     * 이미지 4개를 어떤 방식으로 가져올까
     * <p>
     * 1.한방쿼리 20개 기준으로 이미지가 포함된 80개 가져올까
     * 2. 2개쿼리 bookCollection를 구하고 image를 맞춰서 가져올까
     * 3. 아님 더 좋은 방법이 있나
     * <p>
     * 대표이미지 4개를 아예 저장시켜놓을까? Yes or No
     * => 관리하기 힘듦 것 같음 NO
     */
    @Transactional(readOnly = true)
    public CollectionResponses getLikedCollection(Long memberId) {
        List<Collection> collections = collectionRepository.findLikedBookCollection(memberId);

        List<Long> bookCollectionIds = getBookCollectionIds(collections);
        Map<Long, List<String>> imagesMap = imageService.getTop4ImagesMapByBookCollectionId(bookCollectionIds);

        return CollectionResponses.of(collections, imagesMap);
    }

    private List<Long> getBookCollectionIds(List<Collection> collections) {
        return collections.stream()
                .map(Collection::getId)
                .collect(Collectors.toList());
    }


}
