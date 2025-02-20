package com.goodwill.examine.service.impl;

import com.goodwill.examine.dto.DepartMeetingDTO;
import com.goodwill.examine.entity.AttendDoctor;
import com.goodwill.examine.entity.DepartMeeting;
import com.goodwill.examine.mapper.AttendDoctorMapper;
import com.goodwill.examine.mapper.DepartMeetingMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DepartMeetingServiceImplTest {

    @InjectMocks
    private DepartMeetingServiceImpl departMeetingService;

    @Mock
    private DepartMeetingMapper departMeetingMapper;

    @Mock
    private AttendDoctorMapper attendDoctorMapper;

    @Test
    public void testAddDepartMeeting() {
        // 创建测试数据
        DepartMeetingDTO departMeetingDTO = new DepartMeetingDTO();
        departMeetingDTO.setTopic("测试会议主题");
        departMeetingDTO.setHospitalId(1);
        departMeetingDTO.setImages("测试图片");

        // 调用被测方法
        departMeetingService.addDepartMeeting(departMeetingDTO);

        // 验证 DepartMeetingMapper 的插入操作
        DepartMeeting departMeeting = new DepartMeeting();
        departMeeting.setTopic(departMeetingDTO.getTopic());
        departMeeting.setHospitalId(departMeetingDTO.getHospitalId());
        departMeeting.setImages(departMeetingDTO.getImages());
        departMeeting.setCreateTime(LocalDateTime.now());
        departMeeting.setUpdateTime(LocalDateTime.now());
        verify(departMeetingMapper, times(1)).insert(departMeeting);

        // 验证 AttendDoctorMapper 的插入操作
        departMeetingDTO.getAttendDoctorList().forEach(attendDoctorDTO -> {
            AttendDoctor attendDoctor = new AttendDoctor();
            attendDoctor.setDoctorId(attendDoctorDTO.getDoctorId());
            attendDoctor.setDoctorName(attendDoctorDTO.getDoctorName());
            attendDoctor.setDepartMeetingId(departMeeting.getId());
            verify(attendDoctorMapper, times(1)).insert(attendDoctor);
        });
    }
}
