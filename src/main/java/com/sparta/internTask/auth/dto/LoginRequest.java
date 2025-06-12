package com.sparta.internTask.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
        @Schema(
                description = "사용자 이메일 주소",
                example = "user@example.com",
                pattern = ".+@.+\\..+" // 선택: Swagger UI 상에서 형식 힌트로만 쓰임
        )
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @Schema(
                description = "비밀번호 (소문자, 숫자, 특수문자 포함 8자 이상)",
                example = "pass1234!",
                pattern = "^(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$" // 선택
        )
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
                message = "비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상이어야 합니다."
        )
        String password
) {
}
