package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Channels;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class)
public interface ChannelsMapper {
    /**
     * 通过服务器id添加频道
     * @param name 频道名称
     * @param serverId 服务器id
     * @param createdAt 创建时间
     * @param updatedAt 更新时间
     * @return Integer
     */
    @Insert("insert into channels(name, server_id, created_at, updated_at) value (#{name}, #{serverId}, #{createdAt}, #{updatedAt})")
    Integer insertChannel(@Param("name") String name, @Param("serverId") Integer serverId, @Param("createdAt") String createdAt, @Param("updatedAt") String updatedAt);

    /**
     * 通过服务器id查询频道
     * @param serverId 服务器id
     * @return Channels
     */
    @Select("select * from channels where server_id = #{serverId}")
    Channels selectChannelByServerId(@Param("serverId") Integer serverId);

    /**
     * 通过频道id查询频道
     * @param id 频道id
     * @return Channels
     */
    @Select("select * from channels where id = #{id}")
    Channels selectChannelById(@Param("id") Integer id);

    /**
     * 通过用户id查询频道
     * @param userId 用户id
     * @return Channels
     */
    @Select("select * from channels where server_id = (select server_id from users where id = #{userId})")
    Channels selectChannelByUserId(@Param("userId") Integer userId);
}
