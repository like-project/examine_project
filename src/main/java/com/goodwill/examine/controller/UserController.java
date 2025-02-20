package com.goodwill.examine.controller;

import com.goodwill.examine.common.Result;
import com.goodwill.examine.dto.LoginDTO;
import com.goodwill.examine.service.UserService;
import com.goodwill.examine.vo.UserVO;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/login")
    public Result<UserVO> login(@RequestBody LoginDTO loginDTO) {
        UserVO userVO = userService.login(loginDTO);
        return Result.success(userVO);
    }


    @GetMapping(value = "/register")
    public void register() {
        userService.register();
    }

    @PostMapping("/phone/login")
    public Result<UserVO> phoneLogin(@RequestBody LoginDTO loginDTO) {
        UserVO userVO = userService.phoneLogin(loginDTO);
        return Result.success(userVO);
    }
    
    @GetMapping("/sendCaptcha")
    public Result<Void> sendVerifyCode(@RequestBody LoginDTO loginDTO) {
        userService.sendVerifyCode(loginDTO.getPhone());
        return Result.success();
    }




    //性别统计
    @GetMapping("/gender/statistics")
    public Result<Map<String, Long>> genderStatistics() {
        Map<String, Long> integerLongMap = userService.regionStatistics();
        return Result.success(integerLongMap);
    }


    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public ResponseEntity<StreamingResponseBody> streamData(@RequestParam(value = "msg") String msg) {
        String base_url = "https://api.deepseek.com";
        StreamingResponseBody responseBody = outputStream -> {
            for (int i = 0; i < 10; i++) {
                String message = "Message part " + i + "\n";
                outputStream.write(message.getBytes(StandardCharsets.UTF_8));
                outputStream.flush(); // 确保数据被立即发送
                try {
                    Thread.sleep(500); // 模拟延迟
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}   