package book.book.common;

import lombok.Data;

@Data
public class ResponseForm<T> {

    private StatusResponse statusResponse;
    private final T data;

    public ResponseForm() {
        this.data = null;
    }

    /**
        요청 성공 시, 응답 dto 객체를 파라미터로 받음
     */
    public ResponseForm(T data) {
        this.data = data;
        this.statusResponse = new StatusResponse(ResultCode.OK);
    }


    /**
        요청 실패 시, 정의한 에러를 파라미터로 받음
     */
    public ResponseForm(ResultCode resultCode) {
        this();
        this.statusResponse = new StatusResponse(resultCode);
    }
}