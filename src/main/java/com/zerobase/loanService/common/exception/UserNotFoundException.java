package com.zerobase.loanService.common.exception;

public class UserNotFoundException extends RuntimeException {
    // 유저를 찾지 못했을 때
    public UserNotFoundException(String message) {
        super(message);
    }
}
