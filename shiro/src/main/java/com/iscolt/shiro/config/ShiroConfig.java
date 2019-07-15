package com.iscolt.shiro.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Shiro 配置
 *
 * @author iscolt
 * @date 2019/07/13
 */
@Configuration
public class ShiroConfig {
    /**
     * 配置两个用户及权限
     *
     * @return
     */
    @Bean
    public Realm realm() {
        TextConfigurationRealm realm = new TextConfigurationRealm();
        realm.setUserDefinitions("sang=l23,user\n admin=123,admin");
        realm.setRoleDefinitions("admin=read,write\n user=read");
        return realm;
    }

    /**
     * 配置基本过滤规则
     *
     * @return
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition () {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        chainDefinition.addPathDefinition("/login", "anon");
        chainDefinition.addPathDefinition("/doLogin", "anon");
        chainDefinition.addPathDefinition("/logout", "logout");
        chainDefinition.addPathDefinition("/**", "authc");
        return chainDefinition;
    }

    /**
     * 配置在thymeleaf中使用 shiro标签
     *
     * @return
     */
    @Bean
    public ShiroDialect shiroDialect() {
        return new ShiroDialect();
    }
}
