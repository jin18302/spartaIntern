package com.sparta.internTask.member;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository {
    Member save(Member member);

    boolean existNickName(String nickName);

    boolean existPhoneNumber(String phoneNumber);

    boolean existEmail(String email);

    Optional<Member> findByEmail(String email);
}
