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
 * 频道表
频道表存储了所有 Future 频道信息(Channels)实体类
 *
 * @author makejava
 * @since 2023-03-24 21:39:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Channels implements Serializable {
    @Serial
    private static final long serialVersionUID = 592275857221903236L;
    /**
     * 频道ID，主键自增长
     */
    private Integer id;
    /**
     * 频道名称
     */
    private String name;
    /**
     * 频道所属服务器ID，外键关联服务器表
     */
    private Integer serverId;
    /**
     * 频道创建时间
     */
    private Timestamp createdAt;
    /**
     * 最近一次更新时间
     */
    private Timestamp updatedAt;
    /**
     * 服务器状态
     */
    private String category;

}

