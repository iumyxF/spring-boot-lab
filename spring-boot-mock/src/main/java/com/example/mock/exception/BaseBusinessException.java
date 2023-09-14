package com.example.mock.exception;

/**
 * @description: 业务异常
 * @Date 2023/2/11 10:57
 * @author iumyxF
 */
public class BaseBusinessException extends RuntimeException {

    public BaseBusinessException(String message) {
        super(message);
    }

}
