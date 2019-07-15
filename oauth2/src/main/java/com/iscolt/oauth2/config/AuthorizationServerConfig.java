package com.iscolt.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

/**
 * 授权服务器配置
 *
 * @author iscolt
 * @date 2019/07/12
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig
        extends AuthorizationServerConfigurerAdapter {

    // 该对象用来支持password模式
    @Autowired
    AuthenticationManager authenticationManager;

    // 用来缓存， 将token存到redis中
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    // 为刷新对象提供支持
    @Autowired
    UserDetailsService userDetailsService;

    // 密码加密
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(10);
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        // 配置password授权模式
        clients.inMemory()
                .withClient("password")
                .authorizedGrantTypes("password")
                .accessTokenValiditySeconds(1800)
                .resourceIds("rid")
                .scopes("all")
                .secret("5211");
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        // 配置令牌存储
        endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory))
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 配置支持client_id和client_security支持
        security.allowFormAuthenticationForClients();
    }
}
