package com.future.service.imp;

import com.future.entity.Servers;
import com.future.mapper.ServersMapper;
import com.future.service.ServerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ServerServiceImpl implements ServerService {

    @Resource
    ServersMapper serversMapper;

    /**
     * 添加服务器
     *
     * @param servers 服务器对象
     * @return
     */
    @Override
    public Boolean addServer(Servers servers) {
        Integer integer = serversMapper.insertServer(servers);
        return integer >= 1;
    }

    /**
     * 根据服务器id查找服务器
     *
     * @param ownerId 服务器id
     * @return 服务器对象
     */
    @Override
    public Servers getServerByOwnerId(Integer ownerId) {
        return serversMapper.selectServerByOwnerId(ownerId);
    }
}
