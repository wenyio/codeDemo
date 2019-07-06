package com.iscolt.mail.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务
 *
 * @Auther:http://www.idler.work
 * @Date:2019/2/18
 * @Description:work.idler.task.service
 * @version:1.0
 */

@Service
public class ScheduledService {

    /**
     * second, minute, hour, day of month, month, day of week.
     * 0 * * * * MON-FRI  周一到周五 整秒
     * MON TUE WED THU FRI SAT SUN
     */
    //@Scheduled(cron = "0 * * * * MON-FRI")
    //@Scheduled(cron = "0,1,2,3,4 * * * * MON-FRI")
    //@Scheduled(cron = "0-4 * * * * MON-FRI")
    @Scheduled(cron = "0/4 * * * * SUN-SAT") //每4秒启动一次
    public void hello(){
        System.out.println("hello....");
    }
}
