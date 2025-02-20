package com.goodwill.examine.entity;

import lombok.Data;
import java.time.LocalDateTime;

/**
 * 参会医生实体类
 */
@Data
public class AttendDoctor {
    private Integer id; // 主键
    private Integer doctorId; // 医生ID
    private String doctorName; // 医生姓名
    private Integer departMeetingId; // 科室会ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}