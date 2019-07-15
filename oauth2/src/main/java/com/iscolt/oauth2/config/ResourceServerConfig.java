package com.iscolt.oauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 资源服务器配置
 *
 * @author iscolt
 * @date 2019/07/12
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig
        extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 配置资源id， 和授权服务器资源id一致
        resources.resourceId("rid").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 配置HttpSecurity
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated();
    }
}
