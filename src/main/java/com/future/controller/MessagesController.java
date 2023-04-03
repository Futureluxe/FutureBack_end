package com.future.controller;

import com.future.entity.Messages;
import com.future.entity.resp.RestBean;
import com.future.service.MessageService;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * 消息控制器
 */
@RestController
@RequestMapping("/messages")
public class MessagesController {
    @Resource
    private MessageService messageService;

    @Resource
    private SimpMessagingTemplate messagingTemplate;

    /**
     * 发送消息
     * @param content 消息内容
     * @param authorId 发送者id
     * @param channelId 频道id
     * @return 消息对象
     * @throws Exception 异常
     */
    @MessageMapping("/chat") //接收消息
    @SendTo("/topic/chat") //广播 也可以指定用户 例如 /user/1/chat
    public RestBean<Messages> greeting(
            @RequestParam("content") String content,//消息内容
            @RequestParam("author_id") Integer authorId,//发送者id
            @RequestParam("channel_id") Integer channelId//频道id
    ) throws Exception {
        Messages message = new Messages()
                .setContent(content)
                .setAuthorId(authorId)
                .setChannelId(channelId)
                .setCreatedAt(new Timestamp(System.currentTimeMillis()));
        //将消息存到数据库中
        Boolean aBoolean = messageService.insertMessage(message);
        //将消息发送到指定的频道
        messagingTemplate.convertAndSend("/topic/chat", message); //广播 也可以指定用户 /topic/chat
        return aBoolean
                ? new RestBean<>(200, "消息发送成功", message)
                : new RestBean<>(500, "消息发送失败");
    }

    /**
     * 查询历史消息
     * @param channelId    频道id
     * @return 消息列表
     */
    @GetMapping("/history")
    @ResponseBody
    public RestBean<List<Messages>> queryHistory(Integer channelId) {
        List<Messages> messages = messageService.queryHistory(channelId);
        return messages != null
                ? new RestBean<>(200, "查询成功", messages)
                : new RestBean<>(500, "查询失败");
    }
}
