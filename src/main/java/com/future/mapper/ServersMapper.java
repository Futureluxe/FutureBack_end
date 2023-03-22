package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Servers;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

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
            @Result(column = "member_id",property = "memberId")
    })
    @Insert("insert into servers(name,owner_id,created_at,updated_at,category,img,member_id) value (#{name},#{ownerId},#{createdAt},#{updatedAt},#{catrgory},#{img},#{memberId})")
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



}
