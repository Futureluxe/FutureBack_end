package com.future.service;

import com.future.entity.Messages;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MessageService{

    /**
     * 插入消息,私聊的时候toId为接收者id,群聊的时候toId为频道id
     * @param messages 消息对象
     * @return 插入结果
     */
    Boolean insertMessage(Messages messages);

    /**
     * 通过toId查询消息
     * @param toId  接收者id
     * @return  消息列表
     */
    /*Map<String,Messages> findByToId(Integer toId);*/

    /**
     * 查询历史消息
     * @return
     */
    List<Messages> queryHistory(Integer channelId);

}
