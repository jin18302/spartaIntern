package com.sparta.internTask.auth.dto;

import com.sparta.internTask.member.Member;

public record SignUpResponse(Long id, String email, String nickname) {

    public static SignUpResponse from(Member member){
        return new SignUpResponse(member.getId(), member.getEmail(), member.getNickName());
    }
}
