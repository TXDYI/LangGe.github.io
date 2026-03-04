package com.example.zeinengjie.Util;


import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Base64;

@Component
public class JwtBase64 {
    @Value("${jwt.secret}")
    private String jwtBase64;

    private static SecretKey SECRET_KEY;

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(jwtBase64);
        SECRET_KEY = Keys.hmacShaKeyFor(keyBytes);
    }

    public static SecretKey getSigningKey() {
        return SECRET_KEY;
    }
}
