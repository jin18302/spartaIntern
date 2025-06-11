package com.sparta.internTask.exception;

public class UnauthorizedException extends CustomRuntimeException {
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
