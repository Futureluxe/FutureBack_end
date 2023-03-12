package com.future.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {
    // 声明JWT密钥和到期时间（单位：毫秒）
    private final String JWT_SECRET = "springbootjwtdemo";
    private final long JWT_EXPIRATION = 86400000L;

    // 生成JWT令牌
    public String generateToken(UserDetails userDetails) {
        // 创建JWT声明的空映射
        Map<String, Object> claims = new HashMap<>();
        // 使用JJwt库生成JWT令牌
        return Jwts.builder()
                .setClaims(claims)  // 将声明添加到令牌中
                .setSubject(userDetails.getUsername())  // 设置令牌的主题为用户的用户名
                .setIssuedAt(new Date())  // 设置令牌的签发时间为当前时间
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION))  // 设置令牌的到期时间
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)  // 使用HS512算法和JWT密钥进行签名
                .compact();  // 生成JWT字符串并返回
    }

    // 从JWT令牌中获取用户名
    public String getUsernameFromToken(String token) {
        // 使用JJwt库解析JWT令牌并获取主题
        return Jwts.parser()
                .setSigningKey(JWT_SECRET)  // 设置JWT密钥
                .parseClaimsJws(token)  // 解析JWT令牌
                .getBody()  // 获取JWT声明
                .getSubject();  // 获取JWT主题（即用户名）
    }

    // 验证JWT令牌
    public boolean validateToken(String token) {
        try {
            // 使用JJwt库验证JWT令牌的签名和有效期，并返回结果
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            System.out.println("Invalid JWT signature: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT token: " + e.getMessage());
        } catch (ExpiredJwtException e) {
            System.out.println("JWT token is expired: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("JWT token is unsupported: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }
        return false;
    }
}


