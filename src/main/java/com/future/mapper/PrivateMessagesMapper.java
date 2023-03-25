package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.PrivateMessages;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class)
public interface PrivateMessagesMapper {
    /**
     * 获取用户的所有私信
     * @param userId 用户id
     * @return  私信列表
     */
    @Select("SELECT * FROM private_messages WHERE author_id = #{userId} OR reception_id = #{userId}")
    List<PrivateMessages> getAllMessagesForUser(Integer userId);

    /**
     *  发送私信
     * @param privateMessage 私信对象
     */
    @Insert("INSERT INTO private_messages (content, author_id, reception_id, created_at) VALUES (#{content}, #{authorId}, #{receptionId}, NOW())")
    void sendMessage(PrivateMessages privateMessage);
}
