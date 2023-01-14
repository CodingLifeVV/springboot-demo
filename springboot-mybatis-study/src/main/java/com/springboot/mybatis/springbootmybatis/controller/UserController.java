package com.springboot.mybatis.springbootmybatis.controller;

import com.springboot.mybatis.springbootmybatis.mapper.UserMapper;
import com.springboot.mybatis.springbootmybatis.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserMapper userMapper;

    // 查询员工列表所有信息
    @GetMapping("/queryUserList")
    public List<User> queryUserList() {
        List<User> userList = userMapper.queryUserList();

        for (User user : userList){
            System.out.println(user);
        }

        return userList;
    }

    // 通过 id 查询对应员工信息
    @GetMapping("/queryUserById/{id}")
    public User queryUserById(@PathVariable("id") Integer id) {
        User user = userMapper.queryUserById(id);
        return user;
    }

    // 添加一个用户
    @GetMapping("/addUser")
    public String addUser() {
        userMapper.addUser(new User(7,"Jack",123456));
        return "ok";
    }

    // 修改一个用户
    @GetMapping("/updateUser")
    public String updateUser() {
        userMapper.updateUser(new User(7,"JackSon",111456));
        return "ok";
    }

    // 删除一个 User
    @GetMapping("/deleteUser")
    public String deleteUser() {
        userMapper.deleteUser(7);
        return "ok";
    }

}
