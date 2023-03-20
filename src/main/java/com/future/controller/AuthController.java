package com.future.controller;

import com.future.cache.RedisTokenRepository;
import com.future.entity.Users;
import com.future.entity.resp.RestBean;
import com.future.service.AuthServiceImpl;
import com.future.service.UserService;
import com.future.service.VerifyService;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    RedisTokenRepository repository;

    @Resource
    VerifyService verifyService;

    @Resource
    AuthServiceImpl authService;

    @Resource
    UserService userService;


    /**
     * 访问被拒绝
     */
    @PostMapping("/access-deny")
    public RestBean<String> accessDeny() {
        return new RestBean<>(403,"访问被拒绝");
    }

    /**
     * 发送邮箱接口
     * @param email 邮箱
     * @return 发送结果
     */
    @PostMapping("/email")
    public RestBean<Void> sendEmail(@RequestParam("email") String email) {
        verifyService.sendVerifyCode(email);
        return new RestBean<>(200,"发送成功");
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     * @throws IOException IO异常
     */
    @PostMapping("/login")
    public RestBean<PersistentRememberMeToken> login(@RequestParam(value = "username",required = false) String username,
                                 @RequestParam("password") String password,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
            throws IOException {
        try {
            // 使用request.login()进行登录验证
            request.login(username, password);
            // 登录成功，使用response.sendRedirect()进行页面跳转
            response.sendRedirect("/auth/login-success?username="+username);
            return new RestBean<>(200,"Login Success");
        } catch (ServletException e) {
            // 登录失败，使用response.sendRedirect()进行页面跳转
            response.sendRedirect("/auth/login-failure");
            return new RestBean<>(404, "Login Failed");
        }

    }

    /**
     * 登录成功
     * @param username 用户名{后端传输，前端不用管}
     * @return Users对象
     */
    @PostMapping("/login-success")
    public RestBean<PersistentRememberMeToken> loginSuccess(
            @RequestParam("username") String username,
            @RequestParam("mail") String mail,
            @RequestParam("code") String code
    ) {
        Users nameExist = userService.isNameExist(username);
        // 登录成功后，将token存入redis
        String token = UUID.randomUUID().toString(); // 生成token
        // 获取用户名
        Users user = userService.isNameExist(username); // 获取用户信息
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 时间格式化
        String date = sdf.format(new Date()); // 获取当前时间
        Timestamp timestamp = Timestamp.valueOf(date); // 转换为Timestamp类型
        // 封装token
        PersistentRememberMeToken persistentRememberMeToken = new PersistentRememberMeToken(
                user.getUsername(), // 用户名
                // 生成seriesId
                UUID.randomUUID().toString().replace("-", ""),
                token, // token
                timestamp // 过期时间
        );
        repository.createNewToken(persistentRememberMeToken); // 存入redis
        Boolean aBoolean = verifyService.doVerify(mail, code);
        return aBoolean ?
                new RestBean<>(200,"Login Success",persistentRememberMeToken)
                : new RestBean<>(404,"Login Failed,VerifyCode Error");
    }

    /**
     * 登录失败
     */
    @PostMapping("/login-failure")
    public RestBean<String> loginFailure() {
        return new  RestBean<>(402,"Login Failed");
    }

    /**
     * 用户退出登录
     */
    @PostMapping("/logout")
    public RestBean<String> logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            // 使用request.logout()进行退出登录验证
            request.logout();
            // 退出登录成功，使用response.sendRedirect()进行页面跳转
            response.sendRedirect("/api/auth/logout-success");
            return new RestBean<>(200,"Logout Success");
        } catch (ServletException e) {
            return new  RestBean<>(402,"Logout Failed");
        }
    }

    /**
     * 退出登录成功
     */
    @PostMapping("/logout-success")
    public RestBean<String> logoutSuccess() {
        return new RestBean<>(200,"Logout Success");
    }

    /**
     * 请求邮箱验证码
     * @param email 邮箱地址
     * @return {200,邮件发送成功;500,邮件发送失败}
     */
    public RestBean<Void> verifyCode(@RequestParam("email")String email){
        try {
            verifyService.sendVerifyCode(email);
            return new RestBean<>(200,"邮件发送成功");
        }catch (Exception e){
            e.printStackTrace();
            return new RestBean<>(500,"邮件发送失败");
        }
    }
}
