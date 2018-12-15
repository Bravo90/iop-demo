package com.sitech.billing.common.configurations;

import com.sitech.billing.common.shiro.IopShiroRealm;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @author sunzhen
 * @date 2018-11-30 16:23:12
 */
@Configuration
public class ShiroConfiguration {

    @Value("#{${shiro.chain.definition.map}}")
    private Map<String, String> chainDefinitionMap = new LinkedHashMap<>();
    @Value("${shiro.login.url}")
    private String loginUrl;
    @Value("${shiro.success.url}")
    private String successUrl;
    @Value("${shiro.unauthorized.url}")
    private String unauthorizedUrl;

    /**
     * 将自己的验证方式加入容器
     *
     * @return
     */
    @Bean
    public IopShiroRealm iopShiroRealm() {
        IopShiroRealm iopShiroRealm = new IopShiroRealm();
        return iopShiroRealm;
    }

    /**
     * 权限管理，配置主要是Realm的管理认证
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(iopShiroRealm());
        return securityManager;
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        //登录
        shiroFilterFactoryBean.setLoginUrl(loginUrl);
        //成功
        shiroFilterFactoryBean.setSuccessUrl(successUrl);
        //权限认证失败
        shiroFilterFactoryBean.setUnauthorizedUrl(unauthorizedUrl);

        shiroFilterFactoryBean.setFilterChainDefinitionMap(chainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor() {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }
}
