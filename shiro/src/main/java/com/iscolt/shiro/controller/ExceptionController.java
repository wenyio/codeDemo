package com.iscolt.shiro.controller;

import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常控制器
 *
 * @author iscolt
 * @date 2019/07/13
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * 当用户访问未授权的资源时， 跳转到 unauthorized 视图， 并携带出错信息
     *
     * @param e
     * @return
     */
    @ExceptionHandler(AuthorizationException.class)
    public ModelAndView error(AuthorizationException e) {
        ModelAndView mv = new ModelAndView("unauthorized");
        mv.addObject("error", e.getMessage());
        return mv;
    }
}
