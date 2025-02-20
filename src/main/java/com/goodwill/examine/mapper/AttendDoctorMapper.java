package com.goodwill.examine.mapper;

import com.goodwill.examine.entity.AttendDoctor;
import org.apache.ibatis.annotations.Mapper;

/**
 * 参会医生Mapper接口
 */
@Mapper
public interface AttendDoctorMapper {
    int insert(AttendDoctor attendDoctor);
    AttendDoctor selectById(Integer id);
    int update(AttendDoctor attendDoctor);
    int deleteById(Integer id);
}