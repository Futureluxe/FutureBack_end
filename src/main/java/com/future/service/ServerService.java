package com.future.service;

import com.future.entity.Servers;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ServerService {
    /**
     * 添加服务器
     * @param servers 服务器对象
     * @return Boolean
     */
    Boolean addServer(Servers servers);

    /**
     * 根据创建者id查找服务器
     * @param ownerId 创建者id
     * @return 服务器对象
     */
    List<Servers> getServerByOwnerId(Integer ownerId);

    /**
     * 根据服务器id修改服务器类型
     * @param id 用户id
     * @param category 服务器类型
     * @return Boolean
     */
    Boolean updateCategory(Integer id, String category);

    /**
     * 加入服务器
     * @param id       服务器 ID
     * @param memberId 成员 ID
     */
    Boolean joinServer(Integer id, Integer memberId);

    /**
     * 发送消息
     *
     * @param id      服务器 ID
     * @param message 消息内容
     */
    void sendMessage(Integer id, String message);

    /**
     * 退出服务器
     * @param id       服务器 ID
     * @param memberId 成员 ID
     */
    Boolean quitServer(Integer id, Integer memberId);

    /**
     * 根据服务器 ID 查询服务器成员
     * @param id 服务器 ID
     * @return List<Integer>
     */
    Map<String,Servers> getServerMember(Integer id);

    /**
     * 根据用户 ID 查询服务器信息
     * @param id 服务器 ID
     * @return Map<String,Servers>
     */
    Map<String,Servers> getServerMemberByServerId(Integer id);

}
