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
            @Result(column = "img",property = "img"),
            @Result(column = "password",property = "password"),
            @Result(column = "discriminator",property = "discriminator")
    })
    Users findUser(@Param("username") String username);

    /**
     * 添加用户
     * @param user 用户对象
     * @return 添加结果
     */
    @Insert("insert into users(username,email,password,created_at,role,discriminator) values(#{username},#{email},#{password},#{createdAt},#{role},#{discriminator})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id") // 返回主键
    @Results(id = "userMap",value = {
            @Result(column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "role",property = "role"),
            @Result(column = "password",property = "password"),
            @Result(column = "discriminator",property = "discriminator")
    })
    Integer addUser(Users user);

    /**
     * 通过id查询用户
     * @param id 用户id
     * @return Users对象
     */
    @Select("select * from users where id=#{id}")
    @ResultMap("userMap")// 使用上面的@Results
    Users findUserById(@Param("id") Integer id);
}
