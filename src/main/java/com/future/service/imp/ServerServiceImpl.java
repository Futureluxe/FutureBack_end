package com.future.service.imp;


import com.future.entity.Servers;
import com.future.mapper.ServersMapper;
import com.future.service.ServerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Server service.
 */
@Service
public class ServerServiceImpl implements ServerService {

    /**
     * The Servers mapper.
     */
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
     * 根据创建者id查找服务器
     *
     * @param ownerId 创建者id
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
    public Boolean joinServer(Integer id, Integer memberId) {
        // 根据服务器 ID 查询服务器信息
        Servers servers = serversMapper.selectServerById(id);
        if (servers != null) {
            // 更新成员 ID
            Integer integer = serversMapper.insertServerUser(id, memberId);
            return integer >= 1;
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
    public Boolean quitServer(Integer id, Integer memberId) {
        // 根据服务器 ID 查询服务器信息
        Servers servers = serversMapper.selectServerById(id);
        if (servers != null) {
            Integer integer = serversMapper.deleteServerUser(id, memberId);
            return integer >= 1;
        }
        return false;
    }

    /**
     * 根据用户 ID 查询所在的所有服务器信息
     *
     * @param userId 用户 ID
     * @return Map<String, Servers> 以服务器名称为 key，以服务器信息为 value 的 Map 对象
     */
    @Override
    public Map<String, Servers> getServerMember(Integer userId) {
        // 获取该用户所在的所有服务器 ID
        List<Integer> serverIds = serversMapper.selectServerIdByUserId(userId);

        // 初始化返回的 Map 对象
        Map<String, Servers> result = new HashMap<>();

        // 遍历服务器 ID，查询服务器信息并添加到返回的 Map 中
        for (Integer serverId : serverIds) {
            // 根据服务器 ID 查询服务器信息
            Servers server = serversMapper.selectServerById(serverId);

            // 如果查询结果不为空，则将其添加到返回的 Map 中
            if (server != null) {
                result.put("加入的server信息："+server.getName(), server);
            }
        }

        return result;
    }


    /**
     * 根据用户 ID 查询服务器信息及其创建者所在的其他服务器信息
     *
     * @param userID 用户 ID
     * @return Map<String, Servers> 以服务器名称为 key，以服务器信息为 value 的 Map 对象
     */
    @Override
    public Map<String, Servers> getServerMemberByServerId(Integer userID) {
        // 获取该服务器的成员信息
        Map<String, Servers> serverMember = getServerMember(userID);

        // 获取该服务器的创建者所在的其他服务器信息
        List<Servers> serversByOwnerId = getServerByOwnerId(userID);
        for (Servers servers : serversByOwnerId) {
            // 将服务器信息添加到返回的 Map 中，并在服务器名称前添加创造者信息的前缀
            serverMember.put("创造者的服务器: " + servers.getName(), servers);
        }

        return serverMember;
    }
}
