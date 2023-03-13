package com.future.config;

import com.future.cache.RedisTokenRepository;
import com.future.service.AuthServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    AuthServiceImpl service;

    @Resource
    RedisTokenRepository repository;

    @Resource
    private AuthServiceImpl userDetailsService;

    // 配置全局的用户认证信息
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
        //这里使用SpringSecurity提供的BCryptPasswordEncoder
    }

    // 配置密码加密方式
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    /**
     * 配置 Spring Security 的授权规则和登录和登出的路径。
     * @param http Http 安全性
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()   //首先需要配置哪些请求会被拦截，哪些请求必须具有什么角色才能访问
                .antMatchers("/auth/**").permitAll()
                .and()
                .formLogin()
                .loginPage("/auth/access-deny")//默认的登录页路径
                .loginProcessingUrl("/auth/login")//处理登录请求的接口
                /*.successHandler(this::onAuthenticationSuccess)*/
                .successForwardUrl("/auth/login-success")
                .failureForwardUrl("/auth/login-failure")
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessUrl("/auth/logout-success")
                // 关闭CSRF
                .and()
                .csrf()
                .disable()
                .rememberMe()   //开启记住我功能
                .rememberMeParameter("remember")  //登陆请求表单中需要携带的参数，如果携带，那么本次登陆会被记住
                .tokenRepository(repository)  //这里使用的是直接在内存中保存的TokenRepository实现
        //TokenRepository有很多种实现，InMemoryTokenRepositoryImpl直接基于Map实现的，缺点就是占内存、服务器重启后记住我功能将失效
        //后面我们还会讲解如何使用数据库来持久化保存Token信息
        .tokenValiditySeconds(60*2)  //Token的有效时间（秒）默认为14天;单位 秒
        ;
        //开启跨域访问
        http.cors().disable();

    }
}
