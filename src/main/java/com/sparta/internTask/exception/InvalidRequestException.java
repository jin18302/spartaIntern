package com.sparta.internTask.exception;

public class InvalidRequestException extends CustomRuntimeException {
    public InvalidRequestException(ErrorCode errorCode) {super(errorCode);
    }
}
