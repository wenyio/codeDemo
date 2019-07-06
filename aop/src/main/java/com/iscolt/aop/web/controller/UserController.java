package com.iscolt.aop.web.controller;

import com.iscolt.aop.service.UserService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 控制器
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.aop.web.controller
 * @version: 1.0
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/getUserById")
    public String getUserById(Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/deleteUserById")
    public void deleteUserById(Long id) {
        userService.deleteUserById(id);
    }
}
