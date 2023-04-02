package com.future.service.imp;

import com.future.entity.Relationships;
import com.future.entity.Users;
import com.future.mapper.RelationshipsMapper;
import com.future.mapper.UserMapper;
import com.future.service.RelationshipsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service

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
    public Boolean addFriend(Integer userId, Integer friendId, String status, Timestamp createdAt) {
        Integer integer = relationshipsMapper.insertRelationship(userId, friendId, Integer.parseInt(status), createdAt); // 添加 好友
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

    /**
     * 通过用户id查询好友状态
     *
     * @param userId 用户id
     * @return String 状态
     */
    @Override
    public String getStatusByUserId(Integer userId) {
        return relationshipsMapper.getStatusByUserId(userId);
    }

    /**
     * 通过用户id查询好友
     *
     * @param userId 当前用户id
     * @return Map<String, Users>
     */
    @Override
    public Map<String, Users> getFriendByUserId2(Integer userId) {
        List<Relationships> integers = relationshipsMapper.getFriendByUserId(userId);
        Map<String, Users> result = new HashMap<>();
        for (Relationships integer : integers) {
            if (integer.getStatus() == 1) {
                result.put("Friendship"+integer.getId(),userMapper.findUserById(integer.getFriendId()));
            }
        }
        return result;
    }

    /**
     * 修改
     *
     * @param userId   用户id
     * @param friendId 好友id
     * @param status   状态
     * @return Boolean 是否成功
     */
    @Override
    public Boolean updateStatus(Integer userId, Integer friendId, String status) {
        List<Relationships> friendByUserId = relationshipsMapper.getFriendByUserId(userId);// 通过用户id查询好友
        for (Relationships relationships : friendByUserId) {
            if (relationships.getStatus() == 0 && Objects.equals(relationships.getFriendId(), friendId)) { // 判断状态是否为0 并且好友id是否相等
                /*
                 * @param status 状态
                 * @param userId 用户id
                 * @param friendId 好友id
                 */
                Integer integer = relationshipsMapper.updateStatus(Integer.valueOf(status),userId,friendId); // 修改状态
                return integer >= 1;
            }
        }
        return false;
    }


}
