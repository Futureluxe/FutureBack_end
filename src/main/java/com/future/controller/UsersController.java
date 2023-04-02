package com.future.controller;

import com.future.entity.Users;
import com.future.entity.resp.RestBean;
import com.future.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    @Resource
    UserService userService;

    /**
     * 查找用户标识是否存在
     * @param discriminator 用户名
     * @return Users对象
     */
    @PostMapping("find_friends")
    public RestBean<Users> findFriends(
            @RequestParam("discriminator") String discriminator
    ) {
        Users userByDiscriminator = userService.findUserByDiscriminator(discriminator);
        return userByDiscriminator == null
                ? new RestBean<>(402,"用户不存在")
                : new RestBean<>(200,"用户存在",userByDiscriminator);
    }

    /**
     * 添加好友
     * @param discriminator 当前用户discriminator
     * @param discriminator1 好友discriminator
     * @return
     */
    @PostMapping("add_friends")
    public RestBean<Users> addFriends(
            @RequestParam("discriminator") String discriminator,
            @RequestParam("discriminator1") String discriminator1
    ) {
        Users userByDiscriminator = userService.findUserByDiscriminator(discriminator);
        Users userByDiscriminator1 = userService.findUserByDiscriminator(discriminator1);
        if (userByDiscriminator == null || userByDiscriminator1 == null) {
            return new RestBean<>(402,"用户不存在");
        }
        return userService.addFriends(discriminator,discriminator1) != null
                ? new RestBean<>(200,"添加成功")
                : new RestBean<>(500,"添加失败");
    }

    /**
     * 查看用户名是否重复
     * @param username 用户名
     * @return RestBean<Void> \n 200: 用户名可用 \n 402: 用户名重复
     */
    @PostMapping("find_username")
    public RestBean<Void> findDiscriminator(
            @RequestParam("username") String username
    ) {
        Boolean nameRepeat = userService.isNameRepeat(username);
        return nameRepeat
                ? new RestBean<>(402,"用户名重复")
                : new RestBean<>(200,"用户名可用");
    }
}
