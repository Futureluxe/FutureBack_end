package com.future.controller;

import com.future.entity.Users;
import com.future.entity.resp.RestBean;
import com.future.service.RelationshipsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
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
            @RequestParam("created_at") Timestamp createdAt
    ) {
        Boolean aBoolean = relationshipsService.addFriend(userId, friendId, status, createdAt);
        return aBoolean ?
                new RestBean<>(200, "添加成功", relationshipsService.getFriendByUserId(userId))
                : new RestBean<>(500, "添加失败");
    }

    /**
     * 通过用户id查询好友<这个通过id查询用户信息[不推荐使用]>
     * @param userId 用户id
     * @return 返回查询结果
     */
    @RequestMapping("/getFriendByUserId")
    public RestBean<Map<String, Users>> getFriendByUserId(
            @RequestParam("user_id") Integer userId
    ) {
        return new RestBean<>(200, "查询成功", relationshipsService.getFriendByUserId(userId));
    }

    /**
     * 通过用户id查询好友状态
     * @param userId 用户id
     * @return 返回查询结果
     */
    @RequestMapping("/getStatusByUserId")
    public RestBean<String> getStatusByUserId(
            @RequestParam("user_id") Integer userId
    ) {
        String statusByUserId = relationshipsService.getStatusByUserId(userId);// 通过用户id查询好友状态
        return statusByUserId != null ?
                new RestBean<>(200, "查询成功", statusByUserId)
                : new RestBean<>(500, "查询失败");
    }

    /**
     * 通过用户id查询好友<这个通过id查询用户信息[推荐使用]>
     * @param userId 当前用户id
     * @return  返回查询结果
     */
    @PostMapping("/getFriendByUserId2")
    public RestBean<Map<String, Users>> getFriendByUserId2(
            @RequestParam("user_id") Integer userId
    ) {
        Map<String, Users> friendByUserId2 = relationshipsService.getFriendByUserId2(userId); // 通过用户id查询好友
        return friendByUserId2 != null ?
                new RestBean<>(200, "查询成功", friendByUserId2)
                : new RestBean<>(500, "查询失败");
    }

    /**
     * 修改好友状态
     * @param userId 用户id
     * @param friendId 好友id
     * @param status 状态
     * @return 返回修改结果
     */
    @PostMapping("/updateStatus")
    public RestBean<String> updateStatus(
            @RequestParam("user_id") Integer userId,
            @RequestParam("friend_id") Integer friendId,
            @RequestParam("status") String status
    ) {
        Boolean aBoolean = relationshipsService.updateStatus(userId, friendId, status);
        return aBoolean ?
                new RestBean<>(200, "修改成功")
                : new RestBean<>(500, "修改失败");
    }
}
