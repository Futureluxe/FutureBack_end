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
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws IOException
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
     * @return
     */
    @PostMapping("/login-success")
    public RestBean<Users> loginSuccess(
            @RequestParam("username") String username
    ) {
        Users nameExist = userService.isNameExist(username);
        return new RestBean<>(200,"login success",nameExist);
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
