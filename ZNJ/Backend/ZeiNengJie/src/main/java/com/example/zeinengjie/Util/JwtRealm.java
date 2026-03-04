package com.example.zeinengjie.Util;

import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.service.imp.UserImp;
import io.jsonwebtoken.Claims;
import jdk.nashorn.internal.ir.CallNode;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class JwtRealm extends AuthorizingRealm {
    private Logger logger = org.slf4j.LoggerFactory.getLogger(JwtRealm.class);
    private Tool tool = new Tool();
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserImp userImp;
    @Override
    public boolean supports(AuthenticationToken token) {
        logger.info("JwtRealm supports");
        return token instanceof JwtToken;  // 判断是否是 JWT Token
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String jwt = (String) token.getPrincipal();
        try {
        Claims claims = jwtUtil.parseToken(jwt);
            Date issuedAt = claims.getIssuedAt(); // 签发时间
            Date expiration = claims.getExpiration(); // 过期时间
            logger.info("签发时间"+issuedAt+">过期时间"+expiration);
        if (jwtUtil.validateToken(jwt)){
            if (claims.getExpiration().before(new java.util.Date())) {
                throw new ExpiredCredentialsException("Token is expired");  // 如果 Token 过期，抛出异常
            }
        }
        String username = claims.getSubject();
            // 根据 JWT 的信息创建 SimpleAuthenticationInfo 对象
        logger.info(username+"--"+jwt+"---"+getName());
            Subject subject = SecurityUtils.getSubject();
        return new SimpleAuthenticationInfo(username, jwt, getName());  // 将用户名和 JWT 传递给 Shiro
        } catch (Exception e) {
            tool.setStatusCode(401);
            tool.setMessage("Token 解析失败");
            throw new AuthenticationException("Token is invalid", e);  // 如果 Token 解析失败，抛出异常
        }
    }

    /**
     * 授权方法
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("doGetAuthorizationInfo+"+principalCollection.toString());
        String username = principalCollection.toString();
        logger.info("doGetAuthorizationInfo", username);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User userStatus = userImp.getUserStatus(username);
        if (userStatus.getStatus()==0){
            authorizationInfo.addRole("admin");//添加角色
        }
        return authorizationInfo;
    }
}
