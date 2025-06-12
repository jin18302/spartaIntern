package com.sparta.internTask.auth;

import com.sparta.internTask.auth.dto.LoginRequest;
import com.sparta.internTask.auth.dto.LoginResponse;
import com.sparta.internTask.auth.dto.SignUpRequest;
import com.sparta.internTask.auth.dto.SignUpResponse;
import com.sparta.internTask.member.Authority;
import com.sparta.internTask.member.Member;
import com.sparta.internTask.exception.BadRequestException;
import com.sparta.internTask.exception.ErrorCode;
import com.sparta.internTask.exception.NotFoundException;
import com.sparta.internTask.exception.UnauthorizedException;
import com.sparta.internTask.member.MemberRepository;
import com.sparta.internTask.util.JwtUtil;
import com.sparta.internTask.util.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
//@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

//    @Transactional //실질적으로 데이터베이스 사용안함으로 주석처리
    public SignUpResponse signup(SignUpRequest request) {
        if(memberRepository.existEmail(request.email())){throw new com.sparta.internTask.exception.BadRequestException(ErrorCode.DUPLICATE_EMAIL);}
        if(memberRepository.existNickName(request.nickName())){throw new BadRequestException(ErrorCode.DUPLICATE_NICK_NAME);}
        if(memberRepository.existPhoneNumber(request.phoneNumber())){throw new BadRequestException(ErrorCode.DUPLICATE_PHONE_NUMBER);}

        String enCodedPassword = passwordEncoder.encode(request.password());
        Authority authority = Authority.of(request.authority());

        Member member = Member.of(request.nickName(), request.email(), enCodedPassword, request.phoneNumber(), authority);
        memberRepository.save(member);
        return SignUpResponse.from(member);
    }

    public LoginResponse login(LoginRequest request) {
        Member member = memberRepository.findByEmail(request.email()).orElseThrow(() -> new NotFoundException(ErrorCode.MEMBER_NOT_FOUND));
        if (!passwordEncoder.matches(request.password(), member.getPassword())) {throw new UnauthorizedException(ErrorCode.INVALID_PASSWORD);}

        String accessToken = jwtUtil.createToken(member.getId(), member.getEmail(), member.getAuthority());
        return LoginResponse.of(accessToken);
    }

}
