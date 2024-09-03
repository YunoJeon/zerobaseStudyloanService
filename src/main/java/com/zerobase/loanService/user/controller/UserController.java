package com.zerobase.loanService.user.controller;

import com.zerobase.loanService.user.entity.UserInfo;
import com.zerobase.loanService.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "User", description = "유저 정보 API")
public class UserController {

    private final UserService userService;

    @PostMapping("/user")
    @Operation(summary = "유저 정보 수신 API", description = "유저 정보를 받는 API")
    public UserInfo createUser(@RequestBody UserInfo userInfo) {
        if (userInfo.getUsrKey() == null || userInfo.getUsrKey().isEmpty()) {
            throw new IllegalArgumentException("User key can't be null or empty");
        }
        return userService.saveUserInfo(userInfo);
    }

    @GetMapping("/user/{key}")
    @Operation(summary = "유저 정보 조회 API", description = "유저 정보를 조회하는 API")
    public UserInfo getUser(@PathVariable String key) {
        return userService.getUserInfo(key);
    }
}
