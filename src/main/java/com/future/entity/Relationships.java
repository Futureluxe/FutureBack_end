package com.future.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.util.Date;
import java.io.Serializable;

/**
 * 好友表(Relationships)实体类
 *
 * @author makejava
 * @since 2023-03-25 16:42:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Relationships implements Serializable {
    @Serial
    private static final long serialVersionUID = -58171439470413522L;
    /**
     * 好友关系 ID，自动递增
     */
    private Integer id;
    /**
     * 用户 ID
     */
    private Integer userId;
    /**
     * 好友 ID
     */
    private Integer friendId;
    /**
     * 好友请求状态，默认为等待中<br/>
     *         0 --> 等待中<br/>
     *         1 --> 添加成功<br/>
     *         2 --> 删除好友<br/>
     *         3 --> 拒绝添加<br/>
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;

}

