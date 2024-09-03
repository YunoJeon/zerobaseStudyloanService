package com.zerobase.loanService.user.service;

import com.zerobase.loanService.common.exception.UserNotFoundException;
import com.zerobase.loanService.user.entity.UserInfo;
import com.zerobase.loanService.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserInfo saveUserInfo(UserInfo userInfo) {
        return userRepository.save(userInfo);
    }

    public UserInfo getUserInfo(String usrKey) {
        UserInfo userInfo = userRepository.findByUsrKey(usrKey);
        if (userInfo == null) {
            throw new UserNotFoundException("User not found with key: " + usrKey);
        }
        return userRepository.findByUsrKey(usrKey);
    }
}
