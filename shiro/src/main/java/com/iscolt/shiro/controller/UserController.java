package com.iscolt.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 用户控制器
 *
 * @author iscolt
 * @date 2019/07/13
 */
@Controller
public class UserController {

    @PostMapping("/doLogin")
    public String doLogin(String username, String password, Model model) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            model.addAttribute("error", "用户名密码输入错误！");
            return "login";
        }
        return "redirect:/index";
    }

    /**
     * 具有admin 权限才能访问
     *
     * @return
     */
    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    /**
     * 具有admin 或 user 权限才能访问
     *
     * @return
     */
    @RequiresRoles(value = {"admin","user"},logical = Logical.OR)
    @GetMapping("/user")
    public String user() {
        return "user";
    }
}
