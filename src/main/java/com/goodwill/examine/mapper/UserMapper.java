package com.goodwill.examine.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goodwill.examine.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where mobile = #{account} or email = #{account}")
    User findByMobileOrEmail(@Param("account") String account);
} 