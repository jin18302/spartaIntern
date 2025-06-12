package com.sparta.internTask.auth.dto;

import jakarta.validation.constraints.*;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;

public record SignUpRequest(

        @Schema(description = "닉네임 (최대 10자)", example = "이수진")
        @NotBlank
        @Size(max = 10)
        String nickName,

        @Schema(description = "이메일 주소", example = "user@example.com")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @Schema(description = "비밀번호 (소문자, 숫자, 특수문자 포함 8자 이상)", example = "pass1234!")
        @Pattern(
                regexp = "^(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{8,}$",
                message = "비밀번호는 소문자, 숫자, 특수문자를 포함한 8자 이상이어야 합니다."
        )
        String password,

        @Schema(description = "전화번호 (010-0000-0000 형식)", example = "010-1234-5678")
        @NotBlank
        @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
        String phoneNumber,

        @Schema(description = "권한 (예: USER 또는 ADMIN)", example = "USER")
        @NotBlank
        String authority

) {}
