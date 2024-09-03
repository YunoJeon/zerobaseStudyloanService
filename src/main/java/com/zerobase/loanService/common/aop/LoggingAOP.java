package com.zerobase.loanService.common.aop;

import com.zerobase.loanService.common.annotation.Encrypt;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Slf4j
@Component
@Aspect
public class LoggingAOP {

    // @RestController 의 모든 메서드
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {

    }

    // 모든 메서드
    @Pointcut("execution(* *(..))")
    public void allMethods() {

    }

    // 메서드 호출 전 로그 기록
    @Before("restController() && allMethods()")
    public void logBefore(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                log.info("Entering method: {} with arguments: {}",
                        joinPoint.getSignature().toShortString(),
                        maskSensitiveData(arg));
            }
        }
    }

    // 메서드 호출 후 로그 기록
    @AfterReturning(pointcut = "restController() && allMethods()", returning = "result")
    public void logAfter(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: {} with result: {}",
                joinPoint.getSignature().toShortString(),
                maskSensitiveData(result));
    }

    private Object maskSensitiveData(Object obj) {
        if (obj == null) return null;
        try {
            for (Field field : obj.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Encrypt.class)) {
                    field.setAccessible(true);
                    field.set(obj, "***");
                }
            }
        } catch (IllegalAccessException e) {
            log.error("Error masking sensitive data", e);
        }
        return obj;
    }
}
