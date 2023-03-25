package com.future.controller;

import com.future.entity.PrivateMessages;
import com.future.entity.resp.RestBean;
import com.future.service.PrivateMessagesService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/privateMessages")
public class PrivateMessagesController {
    @Resource
    private PrivateMessagesService privateMessagesService;

    /**
     * 发送私信
     * @param content 内容
     * @param userId 发送者id
     * @param receptionId 接收者id
     * @param createdAt 创建时间
     */
    @PostMapping("/sendPrivateMessages")
    public RestBean<String> sendPrivateMessages(
            @RequestParam("content") String content,
            @RequestParam("author_id") Integer userId,
            @RequestParam("reception_id") Integer receptionId,
            @RequestParam("created_at") Timestamp createdAt
    ) {
        Boolean aBoolean = privateMessagesService.sendMessage(new PrivateMessages()
                .setContent(content)
                .setAuthorId(userId)
                .setReceptionId(receptionId)
                .setCreatedAt(createdAt)
        );
        return aBoolean ?
                new RestBean<>(200, "发送成功")
                : new RestBean<>(500, "发送失败");
    }

    /**
     * 获取用户的所有私信
     * @param userId 用户id
     * @return
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
}
