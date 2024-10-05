package book.book.common;

import lombok.Getter;

@Getter
public enum ResultCode {

    // 정상 처리
    OK("P000", "요청 정상 처리"),

    // 서버 내부 에러 (5xx 에러)
    INTERNAL_SERVER_ERROR("P100", "서버 에러 발생"),

    // F2xx: JSon 값 예외
    NOT_VALIDATION("P200", "json 값이 올바르지 않습니다."),

    // P3xx: 인증, 권한에 대한 예외
    AUTH_MEMBER_NOT("P300", "현재 권한으로 접근 불가능합니다."),
    JWT_DATE_NOT("P301", "JWT토큰이 만료되었습니다."),
    REFRESHTOKEN_OUTDATED("P302", "새로 발급된 토큰보다 이전의 리프레시 토큰입니다."),

    // P4xx: 유저 예외
    MEMBER_NOT_FOUND("P400", "존재하지 않는 유저입니다.");



    private final String code;
    private final String message;


    ResultCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
