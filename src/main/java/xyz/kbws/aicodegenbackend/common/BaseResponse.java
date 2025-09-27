package xyz.kbws.aicodegenbackend.common;

import lombok.Data;
import xyz.kbws.aicodegenbackend.exception.ErrorCode;

import java.io.Serializable;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Data
public class BaseResponse<T> implements Serializable {

    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}

