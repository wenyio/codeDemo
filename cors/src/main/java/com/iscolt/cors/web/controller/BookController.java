package com.iscolt.cors.web.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 跨域 书控制器
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.cors.web.controller
 * @version: 1.0
 */
@RestController
@RequestMapping(value = "/book")
public class BookController {

    /**
     * value 表示域
     * maxAge 表示请求有效期
     * allowedheaders 表示允许的请求头
     *
     * @param name
     * @return
     */
    @PostMapping("/")
    @CrossOrigin(value = "http://localhost:8086", maxAge = 1800, allowedHeaders = "*")
    public String addBook(String name) {
        return "receive:" +name;
    }

    @DeleteMapping("/{id}")
    @CrossOrigin(value = "http://localhost:8086", maxAge = 1800, allowedHeaders = "*")
    public String deleteBookById(@PathVariable Long id) {
        return String.valueOf(id);
    }
}
