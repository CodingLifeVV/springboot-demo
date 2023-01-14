package com.wyj.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {
    // 在一个特定的时间执行这个方法——Timer
    // cron表达式
    // 秒 分 时 日 月 周几

    /*
        0 49 11 * * ?       每天的 11 点 49 分 00 秒执行
        0 0/5 11,12 * * ?   每天的 11 点和 12 点每个五分钟执行一次
        0 15 10 ? * 1-6     每个月的周一到周六的 10 点 15 分执行一次
        0/2 * * * * ?       每 2 秒执行一次
     */
    @Scheduled(cron = "0/2 * * * * ?")
    public void hello() {
        System.out.println("hello,你被执行了");
    }
}
