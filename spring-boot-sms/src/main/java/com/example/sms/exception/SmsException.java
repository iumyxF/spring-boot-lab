package com.example.sms.exception;

/**
 * @description: 短信异常
 * @Date 2023/2/15 10:30
 * @author iumyxF
 */
public class SmsException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SmsException(String message) {
        super(message);
    }
}