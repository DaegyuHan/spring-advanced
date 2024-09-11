package org.example.expert.config.aop;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class AccessLogRecordAop {

    // 어노테이션 범위 기반 포인트컷
    @Pointcut("@annotation(org.example.expert.config.annotation.AccessLogRecord)")
    public void accessLogRecordAop() {}

    @Around("accessLogRecordAop()")
    public Object adviceAnnotatiton(ProceedingJoinPoint joinPoint) throws Throwable {

        // API 요청 시각 확인
        long startTime = System.currentTimeMillis();

        // HttpServletRequest 를 가져오기
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        // API 요청 URL
        String requestUrl = request.getRequestURI();

        // 요청한 사용자의 ID (헤더 또는 클레임에서 가져올 수 있음)
        long userId = (long) request.getAttribute("userId");
        // JWT 토큰을 통해 추출하거나, 사용자 인증 방식에 맞게 처리
        // userId는 기본적으로 토큰이나 세션 정보에서 가져와야 함

            Object proceed = joinPoint.proceed();

            log.info("::: API 요청 시각 : {}", startTime);
            log.info("::: 요청한 사용자 ID : {}", userId);
            log.info("::: API 요청 URL : {}", requestUrl);

        return proceed;

    }

}
