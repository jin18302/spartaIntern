package com.sparta.internTask.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "이미 가입된 이메일입니다"),
    DUPLICATE_NICK_NAME(HttpStatus.CONFLICT, "이미 존재하는 닉네임입니다"),
    DUPLICATE_PHONE_NUMBER(HttpStatus.CONFLICT, "이미 가입된 전화번호입니다"),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 회원입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "일치하지 않는 비밀번호입니다"),
    UNAUTHORIZED_ACCESS_MESSAGE(HttpStatus.UNAUTHORIZED, "로그인이 필요합니다"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다");


    private final HttpStatus httpStatus;
    private final String message;
}
