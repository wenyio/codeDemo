package com.iscolt.mail.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @Auther:http://www.idler.work
 * @Date:2019/2/18
 * @Description:work.idler.task.service
 * @version:1.0
 */

@Service
public class AsyncService {

    //告诉spring这是一个异步方法
    @Async
    public void hello(){
        try {
            Thread.sleep(3000); //睡上三秒 并不影响控制器里面其他方法执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("处理数据中..");
    }
}