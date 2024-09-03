package com.zerobase.loanService.common.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.zerobase.loanService.type.ErrorCode.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // 상품을 찾지 못했을 때 예외
    @ExceptionHandler(com.zerobase.loanService.common.exception.ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleProductNotFoundException(com.zerobase.loanService.common.exception.ProductNotFoundException e) {
        log.error("ProductNotFoundException: {}", e.getMessage());
        return new ErrorResponse(NOT_FOUND_PRODUCT.getCode(), NOT_FOUND_PRODUCT.getDescription());
    }

    // 유저를 찾지 못했을 때 예외
    @ExceptionHandler(com.zerobase.loanService.common.exception.UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleUserNotFoundException(com.zerobase.loanService.common.exception.UserNotFoundException e) {
        log.error("UserNotFoundException: {}", e.getMessage());
        return new ErrorResponse(NOT_FOUND_USER.getCode(), NOT_FOUND_USER.getDescription());
    }
    // 잘못된 인자값으로 인한 예외
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException: {}", e.getMessage());
        return new ErrorResponse(INVALID_REQUEST.getCode(), INVALID_REQUEST.getDescription());
    }

    // 검증 오류 발생 시 예외
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleConstraintViolationException(ConstraintViolationException e) {
        log.error("ConstraintViolationException: {}", e.getMessage());
        return new ErrorResponse(CONSTRAINT_VIOLATION.getCode(), CONSTRAINT_VIOLATION.getDescription());
    }

    // 데이터 무결성 예외
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        log.error("DataIntegrityViolationException: {}", e.getMessage());
        return new ErrorResponse(DATA_INTEGRITY_VIOLATION.getCode(), DATA_INTEGRITY_VIOLATION.getDescription());
    }

    // 일반적인 예외
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException(Exception e) {
        log.error("Exception: {}", e.getMessage());
        return new ErrorResponse(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getDescription());
    }

    @Data
    @AllArgsConstructor
    public static class ErrorResponse {
        private String errorCode;
        private String message;
    }
}
