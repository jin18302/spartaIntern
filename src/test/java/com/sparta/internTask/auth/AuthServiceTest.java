package com.sparta.internTask.auth;

import com.sparta.internTask.auth.dto.LoginRequest;
import com.sparta.internTask.auth.dto.LoginResponse;
import com.sparta.internTask.auth.dto.SignUpRequest;
import com.sparta.internTask.auth.dto.SignUpResponse;
import com.sparta.internTask.exception.BadRequestException;
import com.sparta.internTask.exception.UnauthorizedException;
import com.sparta.internTask.member.Authority;
import com.sparta.internTask.member.Member;
import com.sparta.internTask.member.MemberRepository;
import com.sparta.internTask.util.JwtUtil;
import com.sparta.internTask.util.PasswordEncoder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @InjectMocks
    AuthService authService;

    @Mock
    MemberRepository memberRepository;

    @Mock
    PasswordEncoder passwordEncoder;

    @Mock
    JwtUtil jwtUtil;

    SignUpRequest signUpRequest = new SignUpRequest(
            "이수진", "email@naver.com", "passwordA13!", "010-4247-9722", Authority.USER.toString()
    );

    Authority authority = Authority.of(signUpRequest.authority());
    String encodedPassword = "encodedPasswordA13!";

    Member savedMember = Member.of(
            signUpRequest.nickName(),
            signUpRequest.email(),
            encodedPassword,  // 암호화된 비밀번호를 그대로 주입
            signUpRequest.phoneNumber(),
            authority
    );


    @Test
    void 회원가입_성공() {
        // given
        ReflectionTestUtils.setField(savedMember, "id", 1L);

        when(memberRepository.existEmail(signUpRequest.email())).thenReturn(false);
        when(memberRepository.existNickName(signUpRequest.nickName())).thenReturn(false);
        when(memberRepository.existPhoneNumber(signUpRequest.phoneNumber())).thenReturn(false);
        when(passwordEncoder.encode(signUpRequest.password())).thenReturn(encodedPassword);
        when(memberRepository.save(any(Member.class))).thenAnswer(invocation -> {
            Member member = invocation.getArgument(0);
            ReflectionTestUtils.setField(member, "id", 1L); // id 설정
            return member;
        });

        // when
        SignUpResponse response = authService.signup(signUpRequest);

        // then
        assertThat(response.id()).isEqualTo(1L);
        assertThat(response.email()).isEqualTo(signUpRequest.email());
    }

    @Test
    void 회원가입_실패_이메일존재() {
        // given
        when(memberRepository.existEmail(signUpRequest.email())).thenReturn(true);
        // when & then
        assertThrows(BadRequestException.class, () -> authService.signup(signUpRequest));
    }

    @Test
    void 로그인_성공() {
        // given
        LoginRequest loginRequest = new LoginRequest("email@naver.com", "passwordA13!");
        ReflectionTestUtils.setField(savedMember, "id", 1L);
        when(memberRepository.findByEmail(loginRequest.email())).thenReturn(Optional.of(savedMember));
        when(passwordEncoder.matches(loginRequest.password(), encodedPassword)).thenReturn(true);

        String token = "Bearer dsfkmjsdklfjewiofjewkopfksdfl";
        when(jwtUtil.createToken(1L, savedMember.getEmail(), savedMember.getAuthority())).thenReturn(token);

        // when
        LoginResponse response = authService.login(loginRequest);

        // then
        assertThat(response.accessToken()).isEqualTo(token);
    }

    @Test
    void 로그인_실패_비밀번호불일치() {
        // given
        LoginRequest loginRequest = new LoginRequest("email@naver.com", "wrongPassword");
        when(memberRepository.findByEmail(loginRequest.email())).thenReturn(Optional.of(savedMember));
        when(passwordEncoder.matches(loginRequest.password(), encodedPassword)).thenReturn(false);

        // when & then
        assertThrows(UnauthorizedException.class, () -> authService.login(loginRequest));
    }

    @Test
    void 로그인_실패_회원없음() {
        // given
        LoginRequest loginRequest = new LoginRequest("not_exist@naver.com", "somePassword");
        when(memberRepository.findByEmail(loginRequest.email())).thenReturn(Optional.empty());

        // when & then
        assertThrows(com.sparta.internTask.exception.NotFoundException.class, () -> authService.login(loginRequest));
    }
}
