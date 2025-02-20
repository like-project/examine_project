package com.goodwill.examine.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 科室会实体类
 */
@Data
public class DepartMeeting {
    private Integer id; // 主键
    private String topic; // 主题
    private Integer hospitalId; // 医院ID
    private String images; // 图片，多张用逗号隔开
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}