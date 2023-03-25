package com.future.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 服务器和加入用户的对应表(ServerUser)实体类
 *
 * @author makejava
 * @since 2023-03-24 21:39:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ServerUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -89185650754964352L;
    /**
     * 服务器id
     */
    private Integer serverId;
    /**
     * 用户id
     */
    private Integer userId;
    /**
     * 软删除
     */
    private Integer delSoft;

}

