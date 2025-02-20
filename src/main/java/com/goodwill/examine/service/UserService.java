package com.goodwill.examine.service;

import com.goodwill.examine.dto.LoginDTO;
import com.goodwill.examine.vo.UserVO;
import java.util.Map;

public interface UserService {

  UserVO login(LoginDTO loginDTO);

  void sendVerifyCode(String mobile);

  UserVO phoneLogin(LoginDTO loginDTO);

  void register();

  Map<String, Long> regionStatistics();
}