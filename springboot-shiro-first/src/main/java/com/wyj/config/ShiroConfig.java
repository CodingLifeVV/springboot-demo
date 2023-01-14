package com.wyj.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    // ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager")DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        // 设置拦截器链 : anon authc logout roles perms
        Map<String, String> filterMap = new LinkedHashMap<>();

        // 授权
        // 携带 user:add 字符串的用户才能有权限访问 user 文件夹下的 add 页面
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");

        //filterMap.put("/user/add","authc");
        //filterMap.put("/user/update","authc");
        filterMap.put("/user/**","authc");
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置拦截登录的请求(拦截之后跳转的页面)
        bean.setLoginUrl("/toLogin");

        // 设置未授权跳转页面
        bean.setUnauthorizedUrl("/unauth");

        return bean;
    }


    // DefaultWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        // 关联 UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    // 创建 realm 对象, 需要自定义类
    // @Bean(name = "userRealm")
    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }

    // 整合ShiroDialect : 用来整合 Shiro 和 Thymeleaf
    @Bean
    public ShiroDialect getShiroDialect() {
        return new ShiroDialect();
    }


}
