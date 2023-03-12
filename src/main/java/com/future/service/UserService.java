package com.future.service;

import com.future.entity.Users;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    /**
     * 查找用户名是否存在
     * @param name
     * @return
     */
    Users isNameExist(String name);
}
