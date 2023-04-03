package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Channels;
import org.apache.ibatis.annotations.*;

import java.util.Map;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class)
public interface ChannelsMapper {
    /**
     * 插入频道
     * @param id 频道id
     * @param name 频道名
     * @param serverId 服务器id
     * @param createdAt 创建时间
     * @param updatedAt 更新时间
     * @return 插入结果
     */
    @Insert("insert into channels(id,name, server_id, created_at, updated_at) value (#{id},#{name}, #{serverId}, #{createdAt}, #{updatedAt})")
    Integer insertChannel(@Param("id")Integer id ,@Param("name") String name, @Param("serverId") Integer serverId, @Param("createdAt") String createdAt, @Param("updatedAt") String updatedAt);

    /**
     * 通过服务器id查询频道
     * @param serverId 服务器id
     * @return Channels
     */
    @Select("select * from channels where server_id = #{serverId}")
    Map<String,Channels> selectChannelByServerId(@Param("serverId") Integer serverId);

    /**
     * 通过频道id查询频道
     * @param id 频道id
     * @return Channels
     */
    @Select("select * from channels where id = #{id}")
    Map<String,Channels> selectChannelById(@Param("id") Integer id);

    /**
     * 通过用户id查询频道
     * @param userId 用户id
     * @return Channels
     */
    @Select("select * from channels where server_id = (select server_id from users where id = #{userId})")
    Map<String,Channels> selectChannelByUserId(@Param("userId") Integer userId);
}
