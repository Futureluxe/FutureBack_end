package com.future.service;

import org.springframework.stereotype.Service;

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


}
