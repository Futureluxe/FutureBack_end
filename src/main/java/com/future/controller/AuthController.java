package com.future.controller;

import com.future.entity.Users;
import com.future.entity.resp.RestBean;
import com.future.service.UserService;
import com.future.service.VerifyService;
import com.future.util.SnowflakeIDAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Random;

/**
 * 认证控制器
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Resource
    VerifyService verifyService;

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
    public RestBean<Users> loginSuccess(
            @RequestParam("username") String username,
            @RequestParam("mail") String mail,
            @RequestParam("code") String code
    ) {
        // 获取用户名
        Users user = userService.isNameExist(username); // 获取用户信息
        Boolean aBoolean = verifyService.doVerify(mail, code);
        return aBoolean ?
                new RestBean<>(200,"Login Success",user)
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
            response.sendRedirect("/auth/logout-success");
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

    /**
     * 用户注册
     * @param username 用户名
     * @param password 密码
     * @param mail 邮箱
     * @param code 验证码
     * @param role 角色
     * @return 注册结果
     */
    @PostMapping("/register")
    public RestBean<Void> register(@RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("mail") String mail,
                                   @RequestParam("code") String code,
                                   @RequestParam("role") String role
    ) {
        Boolean aBoolean = verifyService.doVerify(mail, code);
        Random random = new Random();
        // 生成随机数 0-10
        int i = random.nextInt(10);
        Random random1 = new Random();
        // 生成随机数 0-10
        int i1 = random1.nextInt(10);

        if (aBoolean) {
            Users user = new Users()
                    .setUsername(username)
                    .setPassword(new BCryptPasswordEncoder().encode(password))
                    .setEmail(mail)
                    .setCreatedAt(new Timestamp(System.currentTimeMillis()))
                    .setDiscriminator(String.valueOf(new SnowflakeIDAlgorithm(i,i1).nextId()))
                    .setRole(role);
            userService.addUser(user);
        }
        return aBoolean ?
                new RestBean<>(200,"Register Success")
                : new RestBean<>(404,"Register Failed,VerifyCode Error");
    }
}
