package com.zerobase.loanService.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    NOT_FOUND_PRODUCT("NOT_FOUND_PRODUCT", "Not Found Product"),
    NOT_FOUND_USER("NOT_FOUND_USER", "Not Found User"),
    INVALID_REQUEST("INVALID_REQUEST", "Invalid Request Parameter"),
    CONSTRAINT_VIOLATION("CONSTRAINT_VIOLATION", "Constraint Violation Occurred"),
    DATA_INTEGRITY_VIOLATION("DATA_INTEGRITY_VIOLATION", "Data Integrity Violation Occurred"),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "An unexpected error occurred");

    private final String code;
    private final String description;
}

