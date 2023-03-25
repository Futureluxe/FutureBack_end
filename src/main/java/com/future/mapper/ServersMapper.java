package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Servers;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
@CacheNamespace(implementation = RedisMybatisCache.class)
public interface ServersMapper {

    /**
     * 新增服务器
     * @param servers 服务器对象
     * @return Integer
     */
    @Results(id="userMap",value={
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "owner_id",property = "ownerId"),
            @Result(column = "created_at",property = "createdAt"),
            @Result(column = "updated_at",property = "updatedAt"),
            @Result(column = "category",property = "category"),
            @Result(column = "img",property = "img"),
    })
    @Insert("insert into servers(name,owner_id,created_at,updated_at,category,img) value (#{name},#{ownerId},#{createdAt},#{updatedAt},#{catrgory},#{img})")
    Integer insertServer(Servers servers);

    /**
     * 通过用户id查询服务器
     * @param ownerId 用户id
     * @return Servers
     */
    @Select("select * from servers where owner_id = #{ownerId}")
    List<Servers> selectServerByOwnerId(@Param("ownerId") Integer ownerId); // 通过用户id查询服务器

    /**
     * 通过服务器id修改服务器,修改category
     * @param ownerId 服务器id
     * @param category 服务器类型
     * @return Integer
     */
    @Update("update servers set category = #{category} where owner_id = #{ownerId}")
    Integer updateServerCategoryByOwnerId(@Param("ownerId") Integer ownerId,
                                          @Param("category") String category);

    /**
     * 根据服务器 ID 查询服务器信息
     * @param id 服务器 ID
     * @return 服务器信息
     */
    @Select("select * from servers where id = #{id}")
    Servers selectServerById(@Param("id") Integer id);

    /**
     * 根据服务器 ID 更新服务器信息
     * @param servers 服务器信息
     * @return 更新结果
     */
    @Update("update servers set name = #{name},category = #{category},img = #{img} where id = #{id}")
    Integer updateServer(Servers servers);

    /**
     * 根据服务器 ID 添加服务器成员
     * @param serverId 服务器 ID
     * @param userId 用户 ID
     * @return 添加结果
     */
    @Insert("insert into server_user(server_id, user_id) value (#{serverId}, #{userId})")
    Integer insertServerUser(@Param("serverId") Integer serverId, @Param("userId") Integer userId);

    /**
     * 根据服务器 ID 删除服务器成员
     * @param serverId 服务器 ID
     * @param userId 用户 ID
     * @return 删除结果
     */
    @Update("update server_user set del_soft = 1 where server_id = #{serverId} and user_id = #{userId}")
    Integer deleteServerUser(@Param("serverId") Integer serverId, @Param("userId") Integer userId);

    /**
     * 根据用户id 查询服务器id
     * @param userId 用户 ID
     * @return 服务器成员
     */
    @Select("select server_id from server_user where user_id = #{userId}")
    List<Integer> selectServerIdByUserId(@Param("userId") Integer userId);


}
