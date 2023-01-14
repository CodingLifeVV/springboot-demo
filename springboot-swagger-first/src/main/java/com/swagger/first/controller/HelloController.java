package com.swagger.first.controller;

import com.swagger.first.pojo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello, Swagger";
    }

    // 只要接口中返回值中存在实体类, 就会被扫描到 Swagger 中
    @PostMapping("/user")
    public User user() {
        return new User();
    }
    
    @ApiOperation("Hello2控制接口")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username) {
        return "hello" + username;
    }
}
