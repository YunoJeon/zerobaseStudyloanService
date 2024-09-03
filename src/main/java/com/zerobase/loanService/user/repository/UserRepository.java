package com.zerobase.loanService.user.repository;

import com.zerobase.loanService.user.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUsrKey(String usrKey);
}
