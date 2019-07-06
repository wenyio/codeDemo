package com.iscolt.multipart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 控制页面跳转
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/24
 * @Description: com.iscolt.multipart.web.controller
 * @version: 1.0
 */
@Controller
public class uploadController {

    @GetMapping("/")
    public String upload() {
        return "upload";
    }
}
