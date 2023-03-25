package com.future.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

/**
 * 频道和加入用户对应表(ChannelUser)实体类
 *
 * @author makejava
 * @since 2023-03-24 21:39:58
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ChannelUser implements Serializable {
    @Serial
    private static final long serialVersionUID = -64266348717859816L;
    /**
     * 频道id
     */
    private Integer chId;
    /**
     * 用户id
     */
    private Integer uid;

}

