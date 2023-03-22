package com.future.service.imp;

import com.future.mapper.ChannelsMapper;
import com.future.service.ChannelsServer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class ChannelsServerImpl implements ChannelsServer {

    @Resource
    private ChannelsMapper channelsMapper;

    /**
     * 通过服务器id添加频道
     *
     * @param name      频道名称
     * @param serverId  服务器id
     * @param createdAt 创建时间
     * @param updatedAt 更新时间
     * @return Boolean
     */
    @Override
    public Boolean insertChannel(String name, Integer serverId, String createdAt, String updatedAt) {
        return channelsMapper.insertChannel(name, serverId, createdAt, updatedAt) > 0;
    }
}
