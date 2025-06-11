package com.sparta.internTask.controller;

import com.sparta.internTask.dto.LoginRequest;
import com.sparta.internTask.dto.LoginResponse;
import com.sparta.internTask.dto.SignUpRequest;
import com.sparta.internTask.dto.SignUpResponse;
import com.sparta.internTask.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignUpRequest request) {

        SignUpResponse signup = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(signup);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        LoginResponse loginResponse = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
