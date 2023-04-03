package com.future.controller;

import com.future.entity.PrivateMessages;
import com.future.entity.resp.RestBean;
import com.future.service.PrivateMessagesService;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 私信控制器
 */
@RestController
@RequestMapping("/privateMessages")
public class PrivateMessagesController {
    @Resource
    private PrivateMessagesService privateMessagesService;

    @Resource
    private SimpMessagingTemplate messagingTemplate;

    public PrivateMessagesController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    /**
     * 发送私信
     * @param message 消息体
     * @param sender 发送者id
     * @param receiver 接收者id
     * @return 返回发送结果
     */
    @MessageMapping("/private_chat") //接收消息
    public RestBean<String> sendPrivateMessages(
            @Payload String message, //消息体
            @Header("author_id") String sender, //消息头
            @Header("reception_id") String receiver //消息头
    ) {
        Boolean aBoolean = privateMessagesService.sendMessage(new PrivateMessages()
                .setContent(message)
                .setAuthorId(Integer.valueOf(sender))
                .setReceptionId(Integer.valueOf(receiver))
                .setCreatedAt(new Timestamp(System.currentTimeMillis()))
        );
        return aBoolean ?
                new RestBean<>(200, "发送成功")
                : new RestBean<>(500, "发送失败");
    }

    /**
     * 获取用户的所有私信
     * @param userId 用户id
     * @return 返回查询结果
     */
    @PostMapping("/getAllMessagesForUser")
    public RestBean<List<PrivateMessages>> getAllMessagesForUser(
            @RequestParam("user_id") Integer userId
    ) {
        List<PrivateMessages> allMessagesForUser = privateMessagesService.getAllMessagesForUser(userId);
        return allMessagesForUser != null ?
                new RestBean<>(200, "查询成功", allMessagesForUser)
                : new RestBean<>(500, "查询失败");
    }

    /**
     * 获取用户的所有私信
     * @param sender 发送者id
     * @param receiver 接收者id
     * @return 返回查询结果
     */
    @GetMapping("/history")
    public List<PrivateMessages> getHistoryMessage(String sender,String receiver){
        return privateMessagesService.getHistoryMessage(Integer.valueOf(sender),Integer.valueOf(receiver));
    }
}
