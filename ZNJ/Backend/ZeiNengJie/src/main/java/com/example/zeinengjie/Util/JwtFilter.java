package com.example.zeinengjie.Util;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JwtFilter extends BasicHttpAuthenticationFilter {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(JwtFilter.class);
    private Tool tool = new Tool();
    /**
     * **判断是否携带 Token（进行拦截）**
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        logger.info("isLoginAttempt -> Authorization header: " + token);
        return StringUtils.hasText(token);
    }
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        //如果携带Token，说明要进行验证
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        logger.info("isAccessAllowed -> Authorization header: " + request.getAttribute("Authorization"));
        if(isLoginAttempt(request,response)){
            try{
                //进入 executeLogin 方法执行登入，检查 token 是否正确
                logger.info("isAccessAllowed -> executeLogin");
                executeLogin(request,response);
                //若无异常，则说明Token有效，放行。接下来继续去验证权限
                return true;
            }catch (Exception e){
                //若有异常，则说明登录的Token无效，直接跳转报错信息
                //注意 ： 登录验证Token相关的异常都会被抛到这里。但是授权相关的异常会抛到系统里，所以需要一个全局异常处理类处理授权异常
//                responseError(response,e.getMessage());
                e.printStackTrace();
                return false;
            }
        }
        if (httpServletRequest.getMethod().equalsIgnoreCase("OPTIONS")) {
            return true;
        }
        //如果请求头不存在 Token，则可能是执行登陆操作或者是游客状态访问，无需检查 token，直接返回 true。
        // 剩下的交给授权注解来检查权限 ：
        //      - 若不需要权限，则可以直接访问
        //      - 若需要权限，则这里没登录授权操作会抛出异常，有全局异常类处理
        logger.info("isAccessAllowed -> no token");
        return isLoginAttempt(request, response) && executeLogin(request, response);
    }

    //防止isAccessAllowed返回false，执行父类的onAccessDenied里的login，重复请求
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        logger.info("onAccessDenied -> no token");
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json;charset=UTF-8");
        logger.info("onAccessDenied -> no token+"+tool.getMessage());
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 未认证
        httpResponse.getWriter().write("{\"message\": \"Unauthorized: Token is invalid or expired\"}");
        return false;
    }
    /**
     * **执行 Shiro 认证逻辑**
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = httpServletRequest.getHeader("Authorization");
        logger.info("executeLogin -> Authorization header: " + token);
        if (token == null || !token.startsWith("Bearer ")) {
            logger.warn("Invalid token format: " + token);
            return false;  // 没有 Token 或者格式不正确，直接拦截
        }
        String jwtToken = token.substring(7);  // 去掉 "Bearer " 前缀
        AuthenticationToken authcToken = new JwtToken(jwtToken);
        Subject subject = getSubject(request, response);
        subject.login(authcToken);  // 执行 Shiro 认证
        return true;
    }
}