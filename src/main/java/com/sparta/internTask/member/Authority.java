package com.sparta.internTask.member;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;

public enum Authority {
    USER, ADMIN;

    public static Authority of(String authority) {
        return Arrays.stream(Authority.values())
                .filter(r -> r.name().equalsIgnoreCase(authority))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "유효하지 않은 UserRole"));
    }
}
