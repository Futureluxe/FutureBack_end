package com.future.service.imp;

import com.future.entity.Messages;
import com.future.mapper.MessageMapper;
import com.future.service.MessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    private MessageMapper messageMapper;


    /**
     * 插入消息,私聊的时候toId为接收者id,群聊的时候toId为频道id
     *
     * @param messages 消息对象
     * @return 插入结果
     */
    @Override
    public Boolean insertMessage(Messages messages) {
        //判断消息是否为空
        if (messages == null) {
            return false;
        }
        if (messages.getAuthorId() == null) {
            return false;
        }
        if (messages.getChannelId() == null) {
            return false;
        }
        return messageMapper.insertMessage(messages);
    }

    /**
     * 通过toId查询消息
     *
     * @param toId 接收者id
     * @return 消息列表
     */
    /*@Override
    public Map<String,Messages> findByToId(Integer toId) {
        List<Messages> byToId = messageMapper.findByToId(toId);
        if (Objects.isNull(byToId)) { //判断消息是否为空 true:为空 false:不为空
            return null;
        }else {
            for (Messages messages : byToId) {
                if (Objects.equals(messages.getChannelId(), toId)) { //Objects.equals()方法:比较两个对象是否相等 Objects.isNull()方法:判断对象是否为空
                    return Map.of("channel Open messages", messages);//频道消息
                } else {
                    return Map.of("private", messages);//私聊 Map.of()方法:返回一个不可变的Map
                }
            }
        }
        return null;
    }*/

    /**
     * 查询历史消息
     *
     * @return 历史消息列表
     */
    @Override
    public List<Messages> queryHistory(Integer channelId) {
        return messageMapper.queryHistory(channelId);
    }
}
