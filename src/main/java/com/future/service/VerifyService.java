package com.future.service;

public interface VerifyService {
    /**
     * 发送验证码
     * @param mail 邮箱
     */
    void sendVerifyCode(String mail);

    /**
     * 验证验证码的正确
     * @param mail 邮箱
     * @param code 验证码
     * @return
     */
    Boolean doVerify(String mail, String code);
}
