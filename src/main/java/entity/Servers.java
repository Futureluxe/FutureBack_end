package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Timestamp;
import java.util.Date;
import java.io.Serializable;

/**
 * 服务器表
服务器表存储了所有 Future 服务器信息(Servers)实体类
 *
 * @author makejava
 * @since 2023-03-13 11:20:50
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Servers implements Serializable {
    private static final long serialVersionUID = 335936988371214489L;
    /**
     * 服务器ID，主键自增长
     */
    private Integer id;
    /**
     * 服务器名称
     */
    private String name;
    /**
     * 服务器创建者ID，外键关联用户表
     */
    private Integer ownerId;
    /**
     * 服务器创建时间
     */
    private Timestamp createdAt;
    /**
     * 最近一次更新时间
     */
    private Timestamp updatedAt;

}

