package com.sparta.internTask.dto;

import com.sparta.internTask.entity.Member;

public record SignUpResponse(Long id, String email, String nickname) {

    public static SignUpResponse from(Member member){
        return new SignUpResponse(member.getId(), member.getEmail(), member.getNickName());
    }
}
