package com.example.zeinengjie.Util;


import com.example.zeinengjie.pojo.User;
import com.example.zeinengjie.service.imp.UserImp;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    Logger logger = org.slf4j.LoggerFactory.getLogger(ShiroConfig.class);
    // 创建 SecurityManager，Shiro 的核心组件
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 配置使用 JwtRealm 来进行认证
        securityManager.setRealm(jwtRealm());

        securityManager.setSessionManager(sessionManager());
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator evaluator = new DefaultSessionStorageEvaluator();
        evaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(evaluator);
        securityManager.setSubjectDAO(subjectDAO);
        logger.info("securityManager");
        return securityManager;
    }
    @Bean
    public DefaultWebSessionManager sessionManager() {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        return sessionManager;
    }
    // 创建 JwtRealm 实例
    @Bean
    public Realm jwtRealm() {
        return new JwtRealm();
    }

    // 配置 ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);
        bean.setUnauthorizedUrl("/unauthorized");
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());  // 使用 JWT 过滤器
        bean.setFilters(filterMap);
        Map<String, String> filterRuleMap = new LinkedHashMap<>();
        filterRuleMap.put("/login", "anon");  // 允许登录
        filterRuleMap.put("/user/**", "jwt");  // 需要 Token 认证
        filterRuleMap.put("/management/*", "jwt,roles[admin]");
        filterRuleMap.put("/**", "anon");  // 放行所有 `OPTIONS` 预检请求
        bean.setFilterChainDefinitionMap(filterRuleMap);
        logger.info("shiroFilter");
        return bean;
    }
}
