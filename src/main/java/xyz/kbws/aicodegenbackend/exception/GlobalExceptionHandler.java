package xyz.kbws.aicodegenbackend.exception;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xyz.kbws.aicodegenbackend.common.BaseResponse;
import xyz.kbws.aicodegenbackend.common.ResultUtil;

/**
 * @author kbws
 * @date 2025/9/27
 * @description:
 */
@Slf4j
@Hidden
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException: ", e);
        return ResultUtil.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException: ", e);
        return ResultUtil.error(ErrorCode.SYSTEM_ERROR, e.getMessage());
    }
}
