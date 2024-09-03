package com.zerobase.loanService.common.aop;

import com.zerobase.loanService.common.annotation.Encrypt;
import com.zerobase.loanService.common.util.EncryptionUtil;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Aspect
@RequiredArgsConstructor
public class EncryptionAOP {

    private final EncryptionUtil encryptionUtil;

    @Around("execution(* com.example.loanservice.user.repository.*.save(..)) || execution(* com.example.loanservice.user.repository.*.find*(..))")
    public Object handleEncryption(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg != null) {
                encryptFields(arg);
            }
        }

        Object returnValue = joinPoint.proceed();

        if (returnValue != null) {
            decryptFields(returnValue);
        }
        return returnValue;
    }

    private void encryptFields(Object obj) throws Exception{
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Encrypt.class)) {
                field.setAccessible(true);
                String originalValue = (String) field.get(obj);
                if (originalValue != null) {
                    String encryptedValue = encryptionUtil.encrypt(originalValue);
                    field.set(obj, encryptedValue);
                }
            }
        }
    }

    private void decryptFields(Object obj) throws Exception{
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Encrypt.class)) {
                field.setAccessible(true);
                String encryptedValue = (String) field.get(obj);
                if (encryptedValue != null) {
                    String decryptedValue = encryptionUtil.decrypt(encryptedValue);
                    field.set(obj, decryptedValue);
                }
            }
        }
    }
}
