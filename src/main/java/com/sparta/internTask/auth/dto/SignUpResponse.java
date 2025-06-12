package com.sparta.internTask.auth.dto;

import com.sparta.internTask.member.Member;
import io.swagger.v3.oas.annotations.media.Schema;

public record SignUpResponse(
        @Schema(description = "회원 ID", example = "1")
        Long id,

        @Schema(description = "이메일 주소", example = "user@example.com")
        String email,

        @Schema(description = "닉네임", example = "이수진")
        String nickName
) {

    public static SignUpResponse from(Member member){
        return new SignUpResponse(member.getId(), member.getEmail(), member.getNickName());
    }
}
