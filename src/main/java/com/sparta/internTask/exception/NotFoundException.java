package com.sparta.internTask.exception;

public class NotFoundException extends CustomRuntimeException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
