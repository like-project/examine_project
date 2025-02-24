package com.goodwill.examine.controller;

import com.goodwill.examine.common.Result;
import com.goodwill.examine.dto.DepartMeetingDTO;
import com.goodwill.examine.service.DepartMeetingService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departMeeting")
public class DepartMeetingController {

  @Autowired
  private DepartMeetingService departMeetingService;

  public static void main(String[] args) {
    String s = UUID.randomUUID().toString().replaceAll("-", "");
    System.out.println(s);
  }


  /**
   * 添加部门会议信息
   *
   * @param departMeetingDTO 部门会议数据传输对象，包含需添加的部门会议详细信息
   *                         （通过@RequestBody接收前端JSON参数）
   * @return 返回统一响应结果，包含操作成功状态标识及空数据体
   *         当HTTP状态码为200时表示操作成功
   */
  @PostMapping(value = "/add")
  public Result<Object> addDepartMeeting(@RequestBody DepartMeetingDTO departMeetingDTO) {
    // 调用服务层执行部门会议添加操作
    departMeetingService.addDepartMeeting(departMeetingDTO);
    return Result.success();
  }

  @GetMapping(value = "/get")
  public Result<Object> getDepartMeeting() {
    // 调用服务层执行部门会议添加操作
    return Result.success();
  }



}