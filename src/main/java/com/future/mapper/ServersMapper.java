package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Servers;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

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
            @Result(column = "img",property = "img")
    })
    @Insert("insert into servers(name,owner_id,created_at,updated_at,category,img) value (#{name},#{ownerId},#{createdAt},#{updatedAt},#{catrgory},#{img})")
    Integer insertServer(Servers servers);

    /**
     * 通过用户id查询服务器
     * @param ownerId 用户id
     * @return Servers
     */
    @Select("select * from servers where owner_id = #{ownerId}")
    Servers selectServerByOwnerId(@Param("ownerId") Integer ownerId); // 通过用户id查询服务器

}
