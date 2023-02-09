package com.example.language.exception;

import com.example.language.entities.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author feng
 * @date 2023/2/9 20:44
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CommonException.class)
    public Result<Object> handleCommonException(CommonException e) {
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }
}

