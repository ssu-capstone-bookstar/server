package book.book.recommending.api;

import book.book.recommending.dto.SaveRecommedingRequest;
import book.book.recommending.service.RecommendingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/recommending")
@RequiredArgsConstructor
@Tag(name = "Recommending", description = "책 추천 API")
public class RecommendingApi {

    private final RecommendingService recommendingService;

    @Operation(summary = "추천 생성")
    @PostMapping("/{recommender_id}")
    public void saveRecommending(@AuthenticationPrincipal Long memberId,
                                 @PathVariable Long recommender_id,
                                 @RequestBody @Valid SaveRecommedingRequest request) {
        recommendingService.saveRecommending(memberId, recommender_id, request);
    }
}