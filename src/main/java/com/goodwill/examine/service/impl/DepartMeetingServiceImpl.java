package com.goodwill.examine.service.impl;

import com.goodwill.examine.dto.DepartMeetingDTO;
import com.goodwill.examine.entity.AttendDoctor;
import com.goodwill.examine.entity.DepartMeeting;
import com.goodwill.examine.mapper.AttendDoctorMapper;
import com.goodwill.examine.mapper.DepartMeetingMapper;
import com.goodwill.examine.service.DepartMeetingService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: ke.li
 * @Date: Created in 2025/2/20 09:15
 * @Version: 1.0
 */
@Service
public class DepartMeetingServiceImpl implements DepartMeetingService {

  @Autowired
  private DepartMeetingMapper departMeetingMapper;
  @Autowired
  private AttendDoctorMapper attendDoctorMapper;

  @Override
  @Transactional(rollbackFor = Exception.class)
  public void addDepartMeeting(DepartMeetingDTO departMeetingDTO) {
    DepartMeeting departMeeting = new DepartMeeting();
    departMeeting.setTopic(departMeetingDTO.getTopic());
    departMeeting.setHospitalId(departMeetingDTO.getHospitalId());
    departMeeting.setImages(departMeetingDTO.getImages());
    departMeeting.setCreateTime(LocalDateTime.now());
    departMeeting.setUpdateTime(LocalDateTime.now());
    departMeetingMapper.insert(departMeeting);
    departMeetingDTO.getAttendDoctorList().forEach(attendDoctorDTO -> {
      AttendDoctor attendDoctor = new AttendDoctor();
      attendDoctor.setDoctorId(attendDoctorDTO.getDoctorId());
      attendDoctor.setDoctorName(attendDoctorDTO.getDoctorName());
      attendDoctor.setDepartMeetingId(departMeeting.getId());
      attendDoctorMapper.insert(attendDoctor);


    });
  }
}
