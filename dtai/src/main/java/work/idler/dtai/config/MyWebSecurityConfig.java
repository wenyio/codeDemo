package work.idler.dtai.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpRequest;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import work.idler.dtai.service.SysUserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring Security 配置
 *
 * @author iscolt
 * @date 2019/07/08
 */

@Configuration
/**
 * prePostEnabled = true 会解锁@PreAuthorize 和 @PostAuthorize 两个注解
 *     @PreAuthorize 在方法前验证
 *     @PostAuthorize 在方法后验证
 *     @PreAuthorize 和 @PostAuthorize 中都可以使用基于表达式的语法
 * securedEnabled = true 会解锁 @Secured 注解
 *     @Secured("ROLE_ADMIN") 表示访问该方法需要 ADMIN 权限
 */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SysUserService sysUserService;

    @Bean
    PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
        /**
         * 密钥迭代次数（密码加密）
         *
         * 注册，添加用户的时候需要BCryptPasswordEncoder加密
         */
        return new BCryptPasswordEncoder();
    }

    @Bean
    RoleHierarchy roleHierarchy() {
        // 角色继承
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_dba > ROLE_admin ROLE_admin > ROLE_user";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 不拦截druid
        http.csrf().ignoringAntMatchers("/druid/*");
//        http.csrf().disable(); // 关闭CSRF保护

        // HttpSecurity 配置
        http.authorizeRequests()
                .antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/db/**").hasRole("dba")
                .antMatchers("/user/**").hasRole("user")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginProcessingUrl("/admin/login").permitAll()
                .and()
                .csrf().disable();

//        // HttpSecurity 配置
//        http.authorizeRequests()
//                .antMatchers("/admin/**").hasRole("ADMIN")
//                .antMatchers("/user/**").access("hasAnyRole('ADMIN','USER')") // 或
//                .antMatchers("/db/**").access("hasRole('DBA') and hasRole('ADMIN')") // 与
//                .and()
//                .formLogin()
//                .loginProcessingUrl("/admin/login").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/admin_login")
//                .loginProcessingUrl("/admin/login")
//                .usernameParameter("username")
//                .passwordParameter("password")
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
//                                                        HttpServletResponse httpServletResponse,
//                                                        Authentication authentication)
//                            throws IOException, ServletException {
//                        Object principal = authentication.getPrincipal();
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        httpServletResponse.setStatus(200);
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("status", 200);
//                        map.put("msg", principal);
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        out.write((objectMapper.writeValueAsString(map)));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .failureHandler(new AuthenticationFailureHandler() {
//                    @Override
//                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
//                                                        HttpServletResponse httpServletResponse,
//                                                        AuthenticationException e)
//                            throws IOException, ServletException {
//                        httpServletResponse.setContentType("application/json;charset=utf-8");
//                        PrintWriter out = httpServletResponse.getWriter();
//                        httpServletResponse.setStatus(401);
//                        Map<String, Object> map = new HashMap<>();
//                        map.put("status", 401);
//                        if (e instanceof LockedException) {
//                            map.put("msg", "账户被锁定，登录失败！");
//                        } else if (e instanceof BadCredentialsException) {
//                            map.put("msg", "账户名或密码输入错误，登录失败！");
//                        } else if (e instanceof DisabledException) {
//                            map.put("msg", "账户被警用，登录失败！");
//                        } else if (e instanceof AccountExpiredException) {
//                            map.put("msg", "账户已过期，登录失败！");
//                        } else if (e instanceof CredentialsExpiredException) {
//                            map.put("msg", "密码已过期，登录失败！");
//                        } else {
//                            map.put("msg", "登录失败！");
//                        }
//                        ObjectMapper objectMapper = new ObjectMapper();
//                        out.write(objectMapper.writeValueAsString(map));
//                        out.flush();
//                        out.close();
//                    }
//                })
//                .permitAll()
//                .and()
//                .logout().logoutUrl("/admin/logout")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .addLogoutHandler(new LogoutHandler() {
//                    @Override
//                    public void logout(HttpServletRequest httpServletRequest,
//                                       HttpServletResponse httpServletResponse,
//                                       Authentication authentication) {
//                    }
//                })
//                .logoutSuccessHandler(new LogoutSuccessHandler() {
//                    @Override
//                    public void onLogoutSuccess(HttpServletRequest httpServletRequest,
//                                                HttpServletResponse httpServletResponse,
//                                                Authentication authentication)
//                            throws IOException, ServletException {
//                        httpServletResponse.sendRedirect("/admin_login");
//                    }
//                });
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(sysUserService);

        // 添加两个用户
//        auth.inMemoryAuthentication()
//                .withUser("root").password("mwy594211.").roles("ADMIN", "USER", "DBA")
//                .and()
//                .withUser("admin").password("594211").roles("ADMIN", "USER")
//                .and()
//                .withUser("user").password("5211").roles("USER");
    }
}
