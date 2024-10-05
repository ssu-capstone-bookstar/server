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
    MEMBER_NOT_FOUND("B400", "존재하지 않는 유저입니다.", HttpStatus.NOT_FOUND);

    private final String code;
    private final String message;
    private final HttpStatus httpstatus;

}
