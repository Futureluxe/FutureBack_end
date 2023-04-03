package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Messages;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class)
public interface MessageMapper {
    /**
     * 插入消息,私聊的时候toId为接收者id,群聊的时候toId为频道id
     * @param messages 消息对象
     * @return 插入结果
     */
    @Select("insert into messages(content, author_id, channel_id, created_at) value ( #{content}, #{authorId}, #{channelId}, #{createdAt})")
    Boolean insertMessage(Messages messages);

    /**
     * 查询历史消息
     * @param channelId 频道id
     * @return 消息列表
     */
    //解释：查询chat_room表中的所有数据，按照time字段排序 降序 desc 升序 asc
    @Select("SELECT * FROM messages where channel_id = #{channelId}  order by created_at ")
    List<Messages> queryHistory(Integer channelId);

}
