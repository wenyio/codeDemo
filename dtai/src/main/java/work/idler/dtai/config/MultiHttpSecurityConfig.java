package work.idler.dtai.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 多个HttpSecurity配置， 用于业务复杂的情况
 *
 * @author iscolt
 * @date 2019/07/11
 */
//@Configuration
public class MultiHttpSecurityConfig {
//    @Bean
//    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Autowired
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        // 添加两个用户
//        auth.inMemoryAuthentication()
//                .withUser("root").password("mwy594211.").roles("ADMIN", "USER", "DBA")
//                .and()
//                .withUser("admin").password("594211").roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("5211").roles("USER");
//    }
//
//    @Configuration
//    @Order(1)
//    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            super.configure(http);
//            // TODO 进行其他配置
//        }
//    }
//
//    @Configuration
//    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter {
//        @Override
//        protected void configure(HttpSecurity http) throws Exception {
//            super.configure(http);
//            // TODO 进行其他配置
//        }
//    }
}
