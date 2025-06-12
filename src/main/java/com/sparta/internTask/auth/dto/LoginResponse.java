package com.sparta.internTask.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record LoginResponse(
        @Schema(
                description = "JWT 액세스 토큰 (Bearer 포함)",
                example = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkw..."
        )
        String accessToken
) {
    public static LoginResponse of(String accessToken){
        return new LoginResponse(accessToken);
    }
}

