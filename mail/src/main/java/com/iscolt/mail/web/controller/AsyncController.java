package com.iscolt.mail.web.controller;

import com.iscolt.mail.service.AsyncService;
import com.iscolt.mail.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 异步任务
 *
 * @Auther:http://www.idler.work
 * @Date:2019/2/18
 * @Description:work.idler.task.controller
 * @version:1.0
 */

@RestController
public class AsyncController {

    @Autowired
    AsyncService asyncService;

    @Autowired
    ScheduledService scheduledService;

    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        scheduledService.hello();
        return "success";
    }
}