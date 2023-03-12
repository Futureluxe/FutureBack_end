package com.future.mapper;

import com.future.entity.Users;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
@Mapper
public interface UserMapper {
    /**
     * 查找用户名
     * @param username 用户名
     * @return Users对象
     */
    @Select("select * from users where username=#{username}")
    Users findUser(@Param("username") String username);
}
