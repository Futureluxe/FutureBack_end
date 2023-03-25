package com.future.controller;

import com.future.entity.Users;
import com.future.entity.resp.RestBean;
import com.future.service.RelationshipsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 好友控制器
 */
@RestController
@RequestMapping("/relationships")
public class RelationshipsController {

    @Resource
    private RelationshipsService relationshipsService;

    /**
     * 添加好友
     * @param userId 用户id
     * @param friendId 好友id
     * @param status 状态
     * @param createdAt 创建时间
     * @return 返回添加结果
     */
    @RequestMapping("/addFriend")
    public RestBean<Map<String, Users>> addFriend(
            @RequestParam("user_id") Integer userId,
            @RequestParam("friend_id") Integer friendId,
            @RequestParam("status") String status,
            @RequestParam("created_at") String createdAt
    ) {
        Boolean aBoolean = relationshipsService.addFriend(userId, friendId, status, createdAt);
        return aBoolean ?
                new RestBean<>(200, "添加成功", relationshipsService.getFriendByUserId(userId))
                : new RestBean<>(500, "添加失败");
    }

    /**
     * 通过用户id查询好友
     * @param userId 用户id
     * @return 返回查询结果
     */
    @RequestMapping("/getFriendByUserId")
    public RestBean<Map<String, Users>> getFriendByUserId(
            @RequestParam("user_id") Integer userId
    ) {
        return new RestBean<>(200, "查询成功", relationshipsService.getFriendByUserId(userId));
    }
}
