package com.sparta.internTask.service;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

    public String checkUserRole(){
        return "권한 체크가 완료되었습니다";
    }
}
