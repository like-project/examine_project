package com.goodwill.examine.mapper;

import com.goodwill.examine.entity.DepartMeeting;
import org.apache.ibatis.annotations.Mapper;

/**
 * 科室会Mapper接口
 */
@Mapper
public interface DepartMeetingMapper {
    int insert(DepartMeeting departMeeting);
    DepartMeeting selectById(Integer id);
    int update(DepartMeeting departMeeting);
    int deleteById(Integer id);
}