package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Relationships;
import org.apache.ibatis.annotations.*;

import java.sql.Timestamp;
import java.util.List;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class)
public interface RelationshipsMapper {
    /**
     * 添加好友方法
     * @param userId 用户id
     * @param friendId 好友id
     * @param status 状态
     * @param createdAt 创建时间
     * @return
     */
    @Insert("insert into relationships(user_id, friend_id, status ,created_at) value (#{userId}, #{friendId},#{status}, #{createdAt})")
    Integer insertRelationship(@Param("userId") Integer userId, @Param("friendId") Integer friendId, @Param("status") Integer status, @Param("createdAt") Timestamp createdAt);

    /**
     * 通过用户id查询好友
     * @param userId 用户id
     * @return Integer
     */
    @Select("select * from relationships where user_id = #{userId}")
    List<Integer> selectRelationshipByUserId(@Param("userId") Integer userId);

    /**
     * 通过用户id查询好友状态
     * @param userId 用户id
     * @return String 状态
     */
    @Select("select status from relationships where user_id = #{userId}")
    String getStatusByUserId(Integer userId);

    /**
     * 通过用户id查询
     * @param userId 用户id
     * @return
     */
    @Select("select * from relationships where user_id = #{userId}")
    List<Relationships> getFriendByUserId(Integer userId);

    /**
     * 修改好友状态
     * @param status 状态
     * @param userId 用户id
     * @param friendId 好友id
     * @return Integer
     */
    @Update("update relationships set status = #{status} where user_id = #{userId} and friend_id = #{friendId}")
    Integer updateStatus(@Param("status") Integer status, @Param("userId") Integer userId, @Param("friendId") Integer friendId);

}
