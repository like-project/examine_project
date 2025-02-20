package com.goodwill.examine.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("user")
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String username;
    private String mobile;
    private String password;
    private Integer gender;
    private String email;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String avatar;
    private String nickname;
    private String region;
}
