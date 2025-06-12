package com.sparta.internTask.auth;

import com.sparta.internTask.auth.dto.LoginRequest;
import com.sparta.internTask.auth.dto.LoginResponse;
import com.sparta.internTask.auth.dto.SignUpRequest;
import com.sparta.internTask.auth.dto.SignUpResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "AUTH-API-GROUP", description = "JWT를 통한 회원가입, 로그인 API를 포함하고있습니다.")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "회원 정보를 등록합니다.")
    @ApiResponse(responseCode = "201", description = "회원가입 성공")
    public ResponseEntity<SignUpResponse> signup(@RequestBody @Valid SignUpRequest request) {

        SignUpResponse signup = authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(signup);
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "회원가입 정보를 통한 로그인을 실행합니다")
    @ApiResponse(responseCode = "200", description = "로그인 성공")
    public ResponseEntity<LoginResponse> login(@RequestBody @Valid LoginRequest request) {

        LoginResponse loginResponse = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(loginResponse);
    }
}
