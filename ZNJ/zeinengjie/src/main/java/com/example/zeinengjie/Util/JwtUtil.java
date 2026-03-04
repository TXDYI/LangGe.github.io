package com.example.zeinengjie.Util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;

@Component  // 让 Spring 管理这个 Bean
public class JwtUtil {

    @Autowired
    private JwtBase64 jwtBase64;

    private static final long EXPIRATION_TIME = 86400000;  // 24小时

    // 生成 Token
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, jwtBase64.getSigningKey()) // 这里可以访问 jwtBase64
                .compact();
    }

    // 解析 Token
    public Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtBase64.getSigningKey())  //统一使用 jwtBase64 获取密钥
                .parseClaimsJws(token)
                .getBody();
    }
    //获取姓名
    public String getUserName(String token) {
        Claims claims = parseToken(token);
        return claims.getSubject();
    }
    // 验证 Token 是否有效
    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
