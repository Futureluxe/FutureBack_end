package com.future.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 私信表(PrivateMessages)实体类
 *
 * @author makejava
 * @since 2023-03-24 21:39:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class PrivateMessages implements Serializable {
    @Serial
    private static final long serialVersionUID = 608221893681928306L;
    /**
     * 私信id
     */
    private Integer id;
    /**
     * 文本内容
     */
    private String content;
    /**
     * 发送者id
     */
    private Integer authorId;
    /**
     * 接收者id
     */
    private Integer receptionId;
    /**
     * 创建时间
     */
    private Timestamp createdAt;

}

