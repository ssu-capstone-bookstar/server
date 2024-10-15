package book.book.search.service;

import book.book.common.CustomException;
import book.book.common.ResultCode;
import book.book.search.dto.aladin.AladinItemLookUpResponse;
import book.book.search.dto.aladin.AladinSearchResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class AladinService {

    private static final int PAGE_SIZE = 20;
    private static final String ALADIN_BASE_URL = "http://www.aladin.co.kr";
    private static final String ITEM_SEARCH_PATH = "/ttb/api/ItemSearch.aspx";
    private static final String ITEM_LOOKUP_PATH = "/ttb/api/ItemLookUp.aspx";

    @Value("${aladin.api.ttbkey}")
    private String ttbKey;

    @Transactional
    public AladinSearchResponse search(String query, Integer start) {
        String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);
        try {
            return WebClient.create(ALADIN_BASE_URL)
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(ITEM_SEARCH_PATH)
                            .queryParam("TTBKey", ttbKey)
                            .queryParam("Query", encodedQuery)
                            .queryParam("QueryType", "Keyword")
                            .queryParam("MaxResults", PAGE_SIZE)
                            .queryParam("start", start)
                            .queryParam("SearchTarget", "Book")
                            .queryParam("output", "js")
                            .build())
                    .retrieve()
                    .bodyToMono(AladinSearchResponse.class)
                    .onErrorMap(this::handleWebClientException)
                    .block();
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ResultCode.ALADIN_API_ERROR);
        }
    }

    public AladinItemLookUpResponse lookupItem(String itemId, String itemIdType) {
        try {
            return WebClient.create(ALADIN_BASE_URL)
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(ITEM_LOOKUP_PATH)
                            .queryParam("TTBKey", ttbKey)
                            .queryParam("ItemId", itemId)
                            .queryParam("ItemIdType", itemIdType)
                            .queryParam("output", "js")
                            .queryParam("OptResult", "authors,toc,story,categoryIdList,bestSellerRank,reviewList,ratingInfo")
                            .build())
                    .retrieve()
                    .bodyToMono(AladinItemLookUpResponse.class)
                    .onErrorMap(this::handleWebClientException)
                    .block();
        } catch (CustomException e) {
            throw e;
        } catch (Exception e) {
            throw new CustomException(ResultCode.ALADIN_API_ERROR);
        }
    }

    /**
     * 알라딘에서 던져주는 예외가 다양하니 메소드로 따로 구현
     */
    private CustomException handleWebClientException(Throwable ex) {
        if (ex instanceof WebClientResponseException wcre) {
            return handleResponseException(wcre);
        } else if (ex instanceof IOException) {
            return new CustomException(ResultCode.ALADIN_NETWORK_ERROR);
        } else {
            return new CustomException(ResultCode.ALADIN_API_ERROR);
        }
    }

    private CustomException handleResponseException(WebClientResponseException wcre) {
        if (wcre.getStatusCode().is4xxClientError()) {
            return new CustomException(ResultCode.ALADIN_INVALID_RESPONSE);
        } else if (wcre.getStatusCode().is5xxServerError()) {
            return new CustomException(ResultCode.ALADIN_API_ERROR);
        } else {
            return new CustomException(ResultCode.ALADIN_API_ERROR);
        }
    }
}