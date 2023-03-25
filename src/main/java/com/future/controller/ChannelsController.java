package com.future.controller;

import com.future.entity.resp.RestBean;
import com.future.service.ChannelsServer;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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





}
