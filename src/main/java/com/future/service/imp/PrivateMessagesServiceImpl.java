package com.future.service.imp;

import com.future.entity.PrivateMessages;
import com.future.mapper.PrivateMessagesMapper;
import com.future.service.PrivateMessagesService;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrivateMessagesServiceImpl implements PrivateMessagesService {

    @Resource
    private PrivateMessagesMapper privateMessagesMapper;

    @Resource
    private SimpMessagingTemplate messagingTemplate;


    /**
     * 获取所有的私信
     *
     * @param userId 用户id
     * @return 私信列表
     */
    @Override
    public List<PrivateMessages> getAllMessagesForUser(Integer userId) {
        return privateMessagesMapper.getAllMessagesForUser(userId);
    }

    /**
     * 发送私信
     *
     * @param privateMessage 私信对象
     */
    @Override
    public Boolean sendMessage(PrivateMessages privateMessage) {
        Integer integer = privateMessagesMapper.sendMessage(privateMessage);
        // 发送消息到对应客户端
        Integer receptionId = privateMessage.getReceptionId();
        messagingTemplate.convertAndSendToUser(receptionId.toString(), "/queue/notifications", privateMessage);
        return integer >= 1;
    }

    /**
     * 通过发送者和接收者查询私信
     *
     * @param sender   发送者
     * @param receiver 接收者
     * @return 私信列表
     */
    @Override
    public List<PrivateMessages> getHistoryMessage(Integer sender, Integer receiver) {
        return privateMessagesMapper.getMessagesBySenderAndReceiver(sender, receiver);
    }
}
