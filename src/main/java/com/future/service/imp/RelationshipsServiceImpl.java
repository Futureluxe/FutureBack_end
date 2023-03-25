package com.future.service.imp;

import com.future.entity.Users;
import com.future.mapper.RelationshipsMapper;
import com.future.mapper.UserMapper;
import com.future.service.RelationshipsService;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RelationshipsServiceImpl implements RelationshipsService {

    @Resource
    private RelationshipsMapper relationshipsMapper;

    @Resource
    private UserMapper userMapper;

    /**
     * 添加好友方法
     *
     * @param userId    用户id
     * @param friendId  好友id
     * @param status    状态
     * @param createdAt 创建时间
     * @return Boolean
     */
    @Override
    public Boolean addFriend(Integer userId, Integer friendId, String status, String createdAt) {
        Integer integer = relationshipsMapper.insertRelationship(userId, friendId, Integer.parseInt(status), createdAt);
        return integer >= 1;
    }

    /**
     * 通过用户id查询好友
     *
     * @param userId 用户id
     * @return Integer
     */
    @Override
    public Map<String, Users> getFriendByUserId(Integer userId) {
        List<Integer> integers = relationshipsMapper.selectRelationshipByUserId(userId);
        Map<String, Users> result = new HashMap<>();
        for (Integer integer : integers) {
            Users userById = userMapper.findUserById(integer);
            userById.setPassword(null);
            userById.setId(null);
            userById.setRole(null);
            result.put(userById.getUsername(),userById);
        }
        return result;
    }
}
