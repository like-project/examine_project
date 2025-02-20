package com.goodwill.examine.dto;

import lombok.Data;

/**
 * @Author: ke.li
 * @Date: Created in 2025/1/21 15:45
 * @Version: 1.0
 */
@Data
public class LoginDTO {
    private String account;      // 账号（手机号或邮箱）
    private String password;     // 密码登录时的密码
    private String verifyCode;   // 验证码登录时的验证码
    private Integer loginType;   // 登录类型：1-密码登录 2-验证码登录
    private String phone;
} 