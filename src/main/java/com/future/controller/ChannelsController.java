package com.future.controller;

import com.future.entity.Channels;
import com.future.entity.resp.RestBean;
import com.future.service.ChannelsServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 频道控制器
 */
@RestController
@RequestMapping("/channels")
public class ChannelsController {

    @Resource
    private ChannelsServer channelsServer;

    /**
     * 添加频道
     * @param name 频道名称
     * @param serverId 服务器id
     * @param createdAt 创建时间
     * @param updatedAt 更新时间
     * @return
     */
    @PostMapping("/add")
    public RestBean<String> addChannels(
            @RequestParam("name") String name,
            @RequestParam("server_id") Integer serverId,
            @RequestParam("created_at") String createdAt,
            @RequestParam("updated_at") String updatedAt
    ){
        Boolean aBoolean = channelsServer.insertChannel(name, serverId, createdAt, updatedAt);
        return aBoolean ?
                new RestBean<>(200,"添加成功")
                : new RestBean<>(500,"添加失败");
    }

    /**
     * 通过服务器id查询频道
     * @param serverId 服务器id
     * @return Channels
     */
    @PostMapping("/selectByServerId")
    public RestBean<Map<String, Channels>> selectByServerId(
            @RequestParam("server_id") Integer serverId
    ){
        Map<String, Channels> stringChannelsMap = channelsServer.selectChannelByServerId(serverId);
        return stringChannelsMap != null ?
                new RestBean<>(200,"查询成功",stringChannelsMap)
                : new RestBean<>(500,"查询失败");
    }

    /**
     * 通过频道id查询频道
     * @param id 频道id
     * @return Channels
     */
    @PostMapping("/selectById")
    public RestBean<Map<String, Channels>> selectById(
            @RequestParam("id") Integer id
    ){
        Map<String, Channels> stringChannelsMap = channelsServer.selectChannelById(id);
        return stringChannelsMap != null ?
                new RestBean<>(200,"查询成功",stringChannelsMap)
                : new RestBean<>(500,"查询失败");
    }

    /**
     * 通过用户id查询频道
     * @param userId 用户id
     * @return Channels
     */
    @PostMapping("/selectByUserId")
    public RestBean<Map<String, Channels>> selectByUserId(
            @RequestParam("user_id") Integer userId
    ){
        Map<String, Channels> stringChannelsMap = channelsServer.selectChannelByUserId(userId);
        return stringChannelsMap != null ?
                new RestBean<>(200,"查询成功",stringChannelsMap)
                : new RestBean<>(500,"查询失败");
    }

}
