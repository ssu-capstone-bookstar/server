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
        요청 실패에 따른 생성자 처리
     */
    public ResponseForm(ResultCode resultCode) {
        this();
        this.statusResponse = new StatusResponse(resultCode);
    }
}