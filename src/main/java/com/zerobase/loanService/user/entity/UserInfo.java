package com.zerobase.loanService.user.entity;

import com.zerobase.loanService.common.annotation.Encrypt;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_info")
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 유저 아이디

    @Column(unique = true)
    private String usrKey; // 유저 키

    @Encrypt
    private String usrRegNum; // 유저 등록 번호(주민번호 등)

    @Encrypt
    private String usrName; // 유저 이름

    private Long usrIcmAmt; // 유저 소득 금액
}
