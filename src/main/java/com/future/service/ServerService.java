package com.future.service;

import com.future.entity.Servers;
import org.springframework.stereotype.Service;

@Service
public interface ServerService {
    /**
     * 添加服务器
     * @param servers 服务器对象
     * @return Boolean
     */
    Boolean addServer(Servers servers);

    /**
     * 根据服务器id查找服务器
     * @param ownerId 服务器id
     * @return 服务器对象
     */
    Servers getServerByOwnerId(Integer ownerId);
}
