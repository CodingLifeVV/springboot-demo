package com.wyj.service;


import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

// 订阅注册中心的服务, 使用买票的服务
// @Service
@Service
public class UserService {

    @Reference
    TicketService ticket;

    public void buyTicket() {
        String tic = ticket.getTicket();
        System.out.println("到注册中心" + tic);
    }

}
