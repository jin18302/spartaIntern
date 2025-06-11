package com.sparta.internTask.auth.dto;

import jakarta.validation.constraints.*;

public record SignUpRequest(
        @NotBlank @Size(max = 10)
        String nickName,
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,
        @Pattern(regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$", message = "비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상이어야 합니다.")
        String password,
        @NotBlank @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$") // 010-0000-0000
        String phoneNumber,
        @NotBlank String authority
) {
}
