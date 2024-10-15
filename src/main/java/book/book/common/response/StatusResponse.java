package book.book.common.response;

import book.book.common.ResultCode;
import lombok.Getter;

@Getter
public class StatusResponse {
    private final String resultCode;
    private final String resultMessage;

    public StatusResponse(ResultCode resultCode) {
        this.resultCode = resultCode.getCode();
        this.resultMessage = resultCode.getMessage();
    }
}