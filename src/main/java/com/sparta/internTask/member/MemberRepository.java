package com.sparta.internTask.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MemberRepository {

    private final Map<Long, Member> inMemory = new HashMap<>();
    private Long id = 1L;

    public Member save(Member member){
        member.setId(id++);
        inMemory.put(member.getId(), member);
        return member;
    }

    public boolean existNickName(String nickName){
       long count = inMemory.values().stream().filter(m -> m.getNickName().equals(nickName)).count();
       return count != 0;
    }

    public boolean existPhoneNumber(String phoneNumber){
        long count = inMemory.values().stream().filter(m -> m.getPhoneNumber().equals(phoneNumber)).count();
        return count != 0;
    }

    public boolean existEmail(String email){
        long count = inMemory.values().stream().filter(m -> m.getEmail().equals(email)).count();
        return count != 0;
    }

    public Optional<Member> findByEmail(String email){
        return inMemory.values().stream().filter(m -> m.getEmail().equals(email)).findFirst();
    }
}
