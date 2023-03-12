package com.future.entity.resp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应实体类的封装
 * @param <T>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestBean<T> {
    /**
     * 状态码
     */
    private Integer code;

    /**
     * 描述信息
     */
    private String reason;

    /**
     * 携带数据内容
     */
    private T data;

    public RestBean(Integer code, String reason) {
        this.code = code;
        this.reason = reason;
    }
}
