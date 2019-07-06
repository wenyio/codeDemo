package com.iscolt.multipart.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * 添加全局数据
 *
 * @Auther: MaWenyi
 * @Date: 2019/6/25
 * @Description: com.iscolt.multipart.config
 * @version: 1.0
 */
@ControllerAdvice
public class GlobalConfig {

    /**
     * 添加全局数据
     *
     * @return
     */
    @ModelAttribute(value = "info")
    public Map<String, String> userInfo() {
        HashMap<String, String> map = new HashMap<>();
        map.put("username", "idler");
        map.put("gender", "男");
        return map;
    }

    // TODO 请求参数预处理
}
