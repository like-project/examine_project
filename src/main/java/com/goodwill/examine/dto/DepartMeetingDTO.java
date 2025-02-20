package com.goodwill.examine.dto;

import com.goodwill.examine.entity.AttendDoctor;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * @Author: ke.li
 * @Date: Created in 2025/2/20 09:11
 * @Version: 1.0
 */
@Data
public class DepartMeetingDTO {

  private Integer id; // 主键
  private String topic; // 主题
  private Integer hospitalId; // 医院ID
  private String images; // 图片，多张用逗号隔开
  private LocalDateTime createTime; // 创建时间
  private LocalDateTime updateTime; // 修改时间
  private List<AttendDoctorDTO> attendDoctorList;

}
