package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Users;
import org.apache.ibatis.annotations.*;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class)
public interface UserMapper {

    /**
     * 查找用户名
     * @param username 用户名
     * @return Users对象
     */
    @Select("select * from users where username=#{username}")
    @Results(id="userMap",value={
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "updated_at",property = "updatedAt"),
            @Result(column = "role",property = "role"),
            @Result(column = "img",property = "img")
    })
    Users findUser(@Param("username") String username);
}
