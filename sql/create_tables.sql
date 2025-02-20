-- 创建科室会主表 depart_meetting
CREATE TABLE depart_meeting (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键自增',
    topic VARCHAR(255) NOT NULL COMMENT '主题',
    hospital_id INT NOT NULL COMMENT '医院id',
    images VARCHAR(1000) COMMENT '图片，多张用逗号隔开',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='科室会主表';

-- 创建参会医生表 attend_doctor
CREATE TABLE attend_doctor (
    id INT AUTO_INCREMENT PRIMARY KEY COMMENT '主键自增',
    doctor_id INT NOT NULL COMMENT '医生id',
    doctor_name VARCHAR(255) NOT NULL COMMENT '医生姓名',
    depart_meeting_id INT NOT NULL COMMENT '科室会主键id',
    create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    FOREIGN KEY (depart_meeting_id) REFERENCES depart_meeting(id) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参会医生表';