package com.future.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
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
        ;
    }

    /**
     * 配置身份认证方式，这里使用了一个简单的内存中的用户信息。
     * @param auth 身份验证管理器生成器
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication() // 配置内存中的用户信息
                .withUser("user").password("{noop}password").roles("USER");
    }
}
