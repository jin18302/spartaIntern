package com.sparta.internTask.aop;

import com.sparta.internTask.annotation.RequireRole;
import com.sparta.internTask.exception.ErrorCode;
import com.sparta.internTask.exception.UnauthorizedException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
@RequiredArgsConstructor
public class RoleCheck {

    private final HttpServletRequest httpRequest;

    @Before("@annotation(requireRole)")
    public void checkBefore(RequireRole requireRole) {
        String requiredRole = requireRole.value();
        String userRole = (String) httpRequest.getAttribute("authority");

        if (userRole == null || !userRole.equalsIgnoreCase(requiredRole)) {
            throw new UnauthorizedException(ErrorCode.ACCESS_FORBIDDEN);
        }
    }

}

