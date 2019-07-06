package com.iscolt.aop.service;

import org.springframework.stereotype.Service;

/**
 * 业务逻辑
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.aop.service
 * @version: 1.0
 */
@Service
public class UserService {

    public String getUserById(Long id) {
        System.out.println("get user by id");
        return "user";
    }

    public void deleteUserById(Long id) {
        System.out.println("delete user by id");
    }
}
