package com.zerobase.loanService.common.exception;

public class ProductNotFoundException extends RuntimeException {
    // 상품을 찾지 못했을 때
    public ProductNotFoundException(String message) {
        super(message);
    }
}
