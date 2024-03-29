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
 * 用户表
用户表存储了所有注册过的 Future 用户信息(Users)实体类
 *
 * @author makejava
 * @since 2023-03-24 21:39:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Users implements Serializable {
    @Serial // 用于序列化
    private static final long serialVersionUID = 405580660338069486L;
    /**
     * 用户ID，主键自增长
     */
    private Integer id;
    /**
     * 用户名，唯一索引
     */
    private String username;
    /**
     * 邮箱，唯一索引
     */
    private String email;
    /**
     * 区分码，唯一索引
     */
    private String discriminator;
    /**
     * 密码的哈希值，使用 bcrypt 算法进行加密
     */
    private String password;
    /**
     * 用户创建时间
     */
    private Timestamp createdAt;
    /**
     * 最近一次更新时间
     */
    private Timestamp updatedAt;
    /**
     * 用户角色
     */
    private String role;
    /**
     * 用户头像
     */
    private String img;

}

