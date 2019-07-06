package com.iscolt.multipart.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 验证全局数据
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.multipart.web.controller
 * @version: 1.0
 */
@Controller
public class HelloController {

    /**
     * 拿全局变量
     *
     * @param model
     */
    @GetMapping("/hello")
    @ResponseBody
    public void hello(Model model) {
        Map<String, Object> map = model.asMap();
        Set<String> keySet = map.keySet();
        // 遍历
        Iterator<String> iterator = keySet.iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value =map.get(key);
            System.out.println(key + ">>>" + value);
        }
    }

    /**
     * 拿全局变量, 简单版
     *
     * @param model
     */
    @GetMapping("/helloEasy")
    @ResponseBody
    public void helloEasy(Model model) {
        Map<String, Object> map = model.asMap();
        System.out.println(map.get("info"));
    }

}
