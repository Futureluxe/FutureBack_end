package com.future.service;

import com.future.entity.Users;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface RelationshipsService {
    /**
     * 添加好友方法
     * @param userId 用户id
     * @param friendId 好友id
     * @param status 状态
     * @param createdAt 创建时间
     * @return Boolean
     */
    Boolean addFriend(Integer userId, Integer friendId,String status, String createdAt);

    /**
     * 通过用户id查询好友
     * @param userId 用户id
     * @return Integer
     */
    Map<String, Users> getFriendByUserId(Integer userId);
}
