package com.goodwill.examine.dto;

import java.time.LocalDateTime;
import lombok.Data;

/**
 * 参会医生实体类
 */
@Data
public class AttendDoctorDTO {
    private Integer id; // 主键
    private Integer doctorId; // 医生ID
    private String doctorName; // 医生姓名
    private Integer departMeetingId; // 科室会ID
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}