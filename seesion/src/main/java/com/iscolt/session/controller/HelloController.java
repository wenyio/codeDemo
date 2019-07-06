package com.iscolt.session.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * 共享Session
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/30
 * @Description: work.idler.dtai.controller
 * @version: 1.0
 */
@Api(value = "Session 共享测试")
@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @ApiOperation(value = "添加名字", notes = "根据参数添加名字")
    @PostMapping("/save")
    public String saveName(String name, HttpSession session) {
        session.setAttribute("name", name);
        return port;
    }

    @ApiOperation(value = "获取名字", notes = "根据name获取值")
    @GetMapping("/get")
    public String getName(HttpSession session) {
        return port + ":" + session.getAttribute("name").toString();
    }
}
