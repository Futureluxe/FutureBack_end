package com.future.service.imp;

import com.future.entity.Users;
import com.future.mapper.UserMapper;
import com.future.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 查找用户名是否存在
     *
     * @param name 前端输入的用户名
     * @return Users对象
     */
    @Override
    public Users isNameExist(String name) {
        return userMapper.findUser(name);
    }

    /**
     * 添加用户
     *
     * @param user 用户对象
     * @return 添加结果
     */
    @Override
    public Integer addUser(Users user) {
        return userMapper.addUser(user);
    }
}
