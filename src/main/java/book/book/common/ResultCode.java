package book.book.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


/**
 * 클라이언트에게 반환할 에러코드
 */
@Getter
@AllArgsConstructor
public enum ResultCode {

    // 정상 처리
    OK("B000", "요청 정상 처리", HttpStatus.OK),

    // 서버 내부 에러 (5xx 에러)
    INTERNAL_SERVER_ERROR("B100", "서버 에러 발생", HttpStatus.INTERNAL_SERVER_ERROR),

    // B2xx: JSon 값 예외
    NOT_VALIDATION("B200", "json 값이 올바르지 않습니다.", HttpStatus.BAD_REQUEST),

    // B3xx: 인증, 권한에 대한 예외
    AUTH_MEMBER_NOT("B300", "현재 권한으로 접근 불가능합니다.", HttpStatus.FORBIDDEN),
    JWT_DATE_NOT("B301", "JWT토큰이 만료되었습니다.", HttpStatus.BAD_REQUEST),
    REFRESHTOKEN_OUTDATED("B302", "새로 발급된 토큰보다 이전의 리프레시 토큰입니다.", HttpStatus.BAD_REQUEST),

    // B4xx: 유저 예외
    MEMBER_NOT_FOUND("B400", "존재하지 않는 유저입니다.", HttpStatus.NOT_FOUND),
    IS_NOT_OWNER("B401", "권한이 없습니다.(주인이 아닙니다)", HttpStatus.BAD_REQUEST),

    // B5xx: book 예외
    BOOK_NOT_FOUND("B500", "존재하지 않는 책입니다.", HttpStatus.NOT_FOUND),
    READING_STATUS_RATING_REQUIRED("B501", "READED상태는 평점이 필수입니다.", HttpStatus.BAD_REQUEST),

    // B6xx: 평가 예외
    MEMBERBOOK_NOT_FOUND("B600", "존재하지 않는 유저책입니다.", HttpStatus.NOT_FOUND),

    // B7xx: Collection 예외
    COLLECTION_NOT_FOUND("B700", "존재하지 않는 북콜렉션입니다.", HttpStatus.NOT_FOUND),
    BOOKCOLLECTION_ALREADY("B701", "나의 북콜렉션 중 같은 이름의 북콜렉션이 존재합니다.", HttpStatus.BAD_REQUEST),

    // B8xx: 알라딘 API 관련 에러
    ALADIN_API_ERROR("B800", "알라딘 API 호출 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    ALADIN_SEARCH_ERROR("B801", "알라딘 도서 검색 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    ALADIN_ITEM_LOOKUP_ERROR("B802", "알라딘 도서 상세 조회 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR),
    ALADIN_NETWORK_ERROR("B803", "알라딘 API 서버와의 통신 중 오류가 발생했습니다.", HttpStatus.SERVICE_UNAVAILABLE),
    ALADIN_INVALID_RESPONSE("B804", "알라딘 API로부터 유효하지 않은 응답을 받았습니다.", HttpStatus.INTERNAL_SERVER_ERROR),

    // B9xx: 기타 예외
    PARE_DATE_STRING_ERROR("B900", "String값을 Date로 파싱도중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String code;
    private final String message;
    private final HttpStatus httpstatus;

}
