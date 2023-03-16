package com.future.mapper;

import com.future.cache.RedisMybatisCache;
import com.future.entity.Servers;
import org.apache.ibatis.annotations.*;

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
    })
    @Insert("insert into servers(name,owner_id,created_at,updated_at) value (#{name},#{ownerId},#{createdAt},#{updatedAt})")
    Integer insertServer(Servers servers);


}
