package com.future.service.imp;

import com.future.entity.PrivateMessages;
import com.future.mapper.PrivateMessagesMapper;
import com.future.service.PrivateMessagesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PrivateMessagesServiceImpl implements PrivateMessagesService {

    @Resource
    private PrivateMessagesMapper privateMessagesMapper;


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
        return integer >= 1;
    }
}
