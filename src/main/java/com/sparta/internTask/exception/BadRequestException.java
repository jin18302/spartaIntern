package com.sparta.internTask.exception;

public class BadRequestException extends CustomRuntimeException {
    public BadRequestException(ErrorCode errorCode) {
        super(errorCode);
    }
}
