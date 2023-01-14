package com.wyj.service;

import com.wyj.service.TicketService;
import org.springframework.stereotype.Component;
import org.apache.dubbo.config.annotation.Service;

@Service    // 被注解的类可以被扫描到, 在项目一启动就自动注册到注册中心
@Component  // 使用 Dubbo 后尽量不要用 Service 注解
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "服务商提供卖票服务";
    }
}
