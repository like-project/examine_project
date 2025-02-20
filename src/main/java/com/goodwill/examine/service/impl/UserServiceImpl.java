package com.goodwill.examine.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.goodwill.examine.entity.User;
import com.goodwill.examine.mapper.UserMapper;
import com.goodwill.examine.service.UserService;
import com.goodwill.examine.dto.LoginDTO;
import com.goodwill.examine.exception.BusinessException;
import com.goodwill.examine.util.RandomUtil;
import com.goodwill.examine.vo.UserVO;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserVO login(LoginDTO loginDTO) {
        User user = userMapper.findByMobileOrEmail(loginDTO.getAccount());
        if (user == null) {
            throw new BusinessException("用户不存在");
        }

        if (loginDTO.getLoginType() == 1) {
            String encode = passwordEncoder.encode(loginDTO.getPassword());
            // 密码登录
            if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
                throw new BusinessException("密码错误");
            }
        } else {
            // 验证码登录
            String cacheCode = redisTemplate.opsForValue().get("verify_code:" + loginDTO.getAccount());
            if (!loginDTO.getVerifyCode().equals(cacheCode)) {
                throw new BusinessException("验证码错误");
            }
        }

        return convertToVO(user);
    }

    @Override
    public void sendVerifyCode(String mobile) {
        // 生成6位随机验证码
        String code = RandomUtil.randomNumbers(6);
        // 保存验证码到Redis，设置5分钟过期
        redisTemplate.opsForValue().set("verify_code:" + mobile, code, 5, TimeUnit.MINUTES);
        // TODO: 调用短信服务发送验证码
        log.info("发送验证码到手机: {}, 验证码: {}", mobile, code);
    }

    @Override
    public UserVO phoneLogin(LoginDTO loginDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(User::getMobile, loginDTO.getPhone()).last("limit 1");
        User user = userMapper.selectOne(queryWrapper);
        return convertToVO(user);
    }

    @Override
    public void register() {
        List<String> list = Arrays.asList("北京", "深圳", "太原", "上海", "天津", "广州", "重庆",
            "石家庄", "北京", "深圳", "太原", "上海", "天津", "广州", "重庆", "石家庄", "北京",
            "深圳", "太原", "上海", "天津", "广州", "重庆", "石家庄");
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        List<User> users = userMapper.selectList(queryWrapper);
        User user = users.get(0);
        user.setId(null);
        for (int i = 0; i < 20; i++) {
            user.setId(null);
            user.setMobile(RandomUtil.randomNumbers(11));
            user.setPassword(passwordEncoder.encode("123456"));
            user.setEmail(RandomUtil.randomString(6) + "@qq.com");
            user.setUsername(RandomUtil.randomString(6));
            user.setGender(RandomUtil.randomInt(0, 2));
            user.setNickname(RandomUtil.randomString(6));
            user.setAvatar("https://img2.baidu.com/it/u=3441296644,3310096614&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=500");
            user.setCreateTime(null);
            user.setUpdateTime(null);
            user.setRegion(list.get(i));
            userMapper.insert(user);
        }
    }

    @Override
    public Map<String, Long> regionStatistics() {
        // 查询所有用户
        List<User> users = userMapper.selectList(null);
        
        // 按region分组并统计每个region的用户数量
        return users.stream()
                    .collect(Collectors.groupingBy(User::getRegion, Collectors.counting()));
    }

    /**
     * 将 User 实体转换为 UserVO
     */
    private UserVO convertToVO(User user) {
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUsername(user.getUsername());
        userVO.setMobile(user.getMobile());
        userVO.setGender(user.getGender());
        userVO.setEmail(user.getEmail());
        userVO.setCreateTime(user.getCreateTime());
        return userVO;
    }
}
