package com.future.service.imp;

import com.future.entity.Channels;
import com.future.mapper.ChannelsMapper;
import com.future.mapper.ServersMapper;
import com.future.service.ChannelsServer;
import com.future.util.SnowflakeIDAlgorithm;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class ChannelsServerImpl implements ChannelsServer {

    @Resource
    private ChannelsMapper channelsMapper;

    @Resource
    private ServersMapper serversMapper;


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
        return channelsMapper.insertChannel(Math.toIntExact(new SnowflakeIDAlgorithm(10,11).nextId()),name, serverId, createdAt, updatedAt) > 0;
    }

    /**
     * 通过服务器id查询频道
     *
     * @param serverId 服务器id
     * @return Channels
     */
    @Override
    public Map<String, Channels> selectChannelByServerId(Integer serverId) {
        return channelsMapper.selectChannelByServerId(serverId);
    }

    /**
     * 通过频道id查询频道
     *
     * @param id 频道id
     * @return Channels
     */
    @Override
    public Map<String, Channels> selectChannelById(Integer id) {
        return channelsMapper.selectChannelById(id);
    }

    /**
     * 通过用户id查询频道
     *
     * @param userId 用户id
     * @return Channels
     */
    @Override
    public Map<String, Channels> selectChannelByUserId(Integer userId) {
        return channelsMapper.selectChannelByUserId(userId);
    }
}
