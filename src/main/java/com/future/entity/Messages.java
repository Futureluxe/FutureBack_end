package com.future.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 消息表
消息表存储了所有 Future 消息信息(Messages)实体类
 *
 * @author makejava
 * @since 2023-03-24 21:39:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Messages implements Serializable {
    @Serial
    private static final long serialVersionUID = -24779403710355938L;
    /**
     * 消息ID，主键自增长
     */
    private Integer id;
    /**
     * 消息内容
     */
    @JsonProperty("content") // 用于序列化和反序列化
    private String content;
    /**
     * 消息发送者ID，外键关联用户表
     */
    @JsonProperty("author")
    private Integer authorId;
    /**
     * 消息所属频道ID，外键关联频道表
     */
    private Integer channelId;
    /**
     * 消息创建时间
     */
    private Timestamp createdAt;

}

