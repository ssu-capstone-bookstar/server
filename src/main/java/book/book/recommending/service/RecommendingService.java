package book.book.recommending.service;

import book.book.member.entity.Member;
import book.book.member.repository.MemberRepository;
import book.book.recommending.dto.SaveRecommedingRequest;
import book.book.recommending.entity.Recommending;
import book.book.recommending.repository.RecommendingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 추천함에 읽으 책을 읽을 시 추천함에 있는 책들을 어떻게 해야 할지 고민해야 합니다.
 * 또한 추천해준 친구에거 알림을 보낼지 말지도요
 */
@Service
@RequiredArgsConstructor
public class RecommendingService {

    private final RecommendingRepository recommendingRepository;
    private final MemberRepository memberRepository;
    private final RecommendingBookService recommendingBookService;

    @Transactional
    public void saveRecommending(Long reccommendedId, Long recommenderId, SaveRecommedingRequest rq) {
        Member reccommended = memberRepository.findByIdOrElseThrow(reccommendedId);
        Member reccommender = memberRepository.findByIdOrElseThrow(recommenderId);

        Recommending recommending = recommendingRepository.findByRecommendedAndRecommender(reccommended, reccommender)
                .orElse(new Recommending(reccommended, reccommender));

        recommendingBookService.saveCollectionBooks(recommending, rq.minmumBookInfoRequests());
    }
}
