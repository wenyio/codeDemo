package com.iscolt.interceptor.web;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 拦截器示列
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.interceptor.web
 * @version: 1.0
 */
public class MyInterceptor implements HandlerInterceptor {

    /**
     * 此方法返回true才会执行
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle : 抱歉, 我第一个执行");
        return true;
    }

    /**
     * 拦截链内, 所有拦截器返回成功时才被调用
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle : 你们都是好人, 我才执行给你们看");
    }

    /**
     * preHandle 返回false 后, 后面的都不执行
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion : 只有preHandle大人发话, 我才放行");
    }
}

