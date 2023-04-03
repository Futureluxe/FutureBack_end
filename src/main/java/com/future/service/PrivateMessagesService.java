package com.future.service;

import com.future.entity.PrivateMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrivateMessagesService {
    /**
     * 获取所有的私信
     * @param userId 用户id
     * @return 私信列表
     */
    public List<PrivateMessages> getAllMessagesForUser(Integer userId);

    /**
     * 发送私信
     * @param privateMessage 私信对象
     */
    public Boolean sendMessage(PrivateMessages privateMessage);

    /**
     * 通过发送者和接收者查询私信
     * @param sender 发送者
     * @param receiver  接收者
     * @return 私信列表
     */
    List<PrivateMessages> getHistoryMessage(Integer sender, Integer receiver);
}
