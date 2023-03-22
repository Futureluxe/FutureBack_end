package com.future.service.imp;


import com.future.entity.Servers;
import com.future.mapper.ServersMapper;
import com.future.service.ServerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
    public List<Servers> getServerByOwnerId(Integer ownerId) {
        return serversMapper.selectServerByOwnerId(ownerId);
    }

    /**
     * 根据服务器id修改服务器类型
     *
     * @param id       用户id
     * @param category 服务器类型
     * @return
     */
    @Override
    public Boolean updateCategory(Integer id, String category) {
        Integer integer = serversMapper.updateServerCategoryByOwnerId(id, category);
        return integer >= 1;
    }

    /**
     * 加入服务器
     *
     * @param id       服务器 ID
     * @param memberId 成员 ID
     */
    @Override
    public Boolean joinServer(Integer id, String memberId) {
        // 根据服务器 ID 查询服务器信息
        Servers servers = serversMapper.selectServerById(id);
        if (servers != null) {
            // 更新成员 ID
            servers.setMemberId(memberId+",");
            // 更新服务器信息
            serversMapper.updateServer(servers);
            return true;
        }
        return false;
    }

    /**
     * 发送消息
     *
     * @param id      服务器 ID
     * @param message 消息内容
     */
    @Override
    public void sendMessage(Integer id, String message) {

    }

    /**
     * 退出服务器
     *
     * @param id       服务器 ID
     * @param memberId 成员 ID
     */
    @Override
    public Boolean quitServer(Integer id, String memberId) {
        // 根据服务器 ID 查询服务器信息
        Servers servers = serversMapper.selectServerById(id);
        if (servers != null) {
            // 更新成员 ID
            servers.setMemberId(servers.getMemberId().replace(memberId+",",""));
            // 更新服务器信息
            serversMapper.updateServer(servers);
            return true;
        }
        return false;
    }
}
