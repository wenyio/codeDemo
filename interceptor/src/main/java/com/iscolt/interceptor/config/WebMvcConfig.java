package com.iscolt.interceptor.config;

import com.iscolt.interceptor.web.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 配置拦截器
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.interceptor.config
 * @version: 1.0
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * addPathPatterns 表示拦截路径
     * excludePatterns 表示排除路径
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/hello");
    }

    /**
     * 路径映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/index").setViewName("index");
    }
}
