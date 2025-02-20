package com.goodwill.examine.vo;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 用户信息返回对象
 */
@Data
public class UserVO {
    private Long id;
    private String username;
    private String mobile;
    private Integer gender;
    private String email;
    private LocalDateTime createTime;
} 