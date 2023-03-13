package entity;

import java.util.Date;
import java.io.Serializable;

/**
 * 用户表
用户表存储了所有注册过的 Future 用户信息(Users)实体类
 *
 * @author makejava
 * @since 2023-03-13 11:20:50
 */
public class Users implements Serializable {
    private static final long serialVersionUID = -98818195186813592L;
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
     * 密码的哈希值，使用 bcrypt 算法进行加密
     */
    private String password;
    /**
     * 用户创建时间
     */
    private Object createdAt;
    /**
     * 最近一次更新时间
     */
    private Date updatedAt;
    /**
     * 用户角色
     */
    private String role;
}

