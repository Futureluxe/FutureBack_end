package com.future.service;

import com.future.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 查找用户名是否存在
     * @param name 用户名
     * @return Users对象
     */
    Users isNameExist(String name);

    /**
     * 添加用户
     * @param user 用户对象
     * @return 添加结果
     */
    Integer addUser(Users user);

    /**
     * 通过discriminator查询用户
     * @param discriminator 用户discriminator
     * @return Users对象
     */
    Users findUserByDiscriminator(String discriminator);

    /**
     * 添加好友
     * @param discriminator 当前用户discriminator
     * @param discriminator1 好友discriminator
     * @return
     */
    Users addFriends(String discriminator, String discriminator1);

    /**
     * 查看用户名是否重复
     * @param name 用户名
     * @return Boolean
     */
    Boolean isNameRepeat(String name);
}
