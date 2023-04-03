package com.future.service;

import com.future.entity.Channels;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public interface ChannelsServer {
    /**
     * 通过服务器id添加频道
     * @param name 频道名称
     * @param serverId 服务器id
     * @param createdAt 创建时间
     * @param updatedAt 更新时间
     * @return Boolean
     */
    Boolean insertChannel(String name, Integer serverId, String createdAt, String updatedAt);

    /**
     * 通过服务器id查询频道
     * @param serverId 服务器id
     * @return Channels
     */
    Map<String, Channels> selectChannelByServerId(Integer serverId);

    /**
     * 通过频道id查询频道
     * @param id 频道id
     * @return Channels
     */
    Map<String, Channels> selectChannelById(Integer id);

    /**
     * 通过用户id查询频道
     * @param userId 用户id
     * @return Channels
     */
    Map<String, Channels> selectChannelByUserId(Integer userId);


}
