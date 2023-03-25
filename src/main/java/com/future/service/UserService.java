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
}
