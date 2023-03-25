package com.future.service;

import com.future.entity.PrivateMessages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrivateMessagesService {
    /**
     * 获取所有的私信
     * @param userId
     * @return 私信列表
     */
    public List<PrivateMessages> getAllMessagesForUser(Integer userId);

    /**
     * 发送私信
     * @param privateMessage 私信对象
     */
    public void sendMessage(PrivateMessages privateMessage);
}
