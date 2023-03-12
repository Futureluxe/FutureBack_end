package com.future.controller;

import com.future.cache.RedisTokenRepository;
import com.future.entity.resp.RestBean;
import com.future.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    RedisTokenRepository tokenRepository;

    /**
     * 访问被拒绝
     */
    @GetMapping("/access-deny")
    public RestBean<String> accessDeny() {
        return new RestBean<>(403,"访问被拒绝");
    }

    /**
     * 用户登录
     */
    @GetMapping("/login")
    public RestBean<String> login(@RequestParam("username") String username,
                                        @RequestParam("password") String password,
                                        HttpServletRequest request,
                                        HttpServletResponse response)
            throws IOException {
        try {
            // 使用request.login()进行登录验证
            request.login(username, password);
            // 登录成功，使用response.sendRedirect()进行页面跳转
            response.sendRedirect("/api/auth/login-success");
            return new RestBean<>(200,"Login Success");
        } catch (ServletException e) {
            // 登录失败，使用response.sendRedirect()进行页面跳转
            response.sendRedirect("/api/auth/login-failure");
            return new RestBean<>(404, "Login Failed");
        }
    }

    /**
     * 登录成功
     */
    @GetMapping("/login-success")
    public RestBean<String> loginSuccess() {
        return new  RestBean<>(200,"Login Success");
    }

    /**
     * 登录失败
     */
    @GetMapping("/login-failure")
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
    @GetMapping("/logout-success")
    public RestBean<String> logoutSuccess() {
        return new RestBean<>(200,"Logout Success");
    }
}
