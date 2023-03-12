package com.future.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;
import java.io.Serializable;

/**
 * 消息表
消息表存储了所有 Future 消息信息(Messages)实体类
 *
 * @author makejava
 * @since 2023-03-12 19:25:10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Messages implements Serializable {
    /**
     * 消息ID，主键自增长
     */
    private Integer id;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 消息发送者ID，外键关联用户表
     */
    private Integer authorId;
    /**
     * 消息所属频道ID，外键关联频道表
     */
    private Integer channelId;
    /**
     * 消息创建时间
     */
    private Date createdAt;
    /**
     * 最近一次更新时间
     */
    private Date updatedAt;

}

