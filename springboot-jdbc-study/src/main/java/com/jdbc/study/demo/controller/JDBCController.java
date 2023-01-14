package com.jdbc.study.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
public class JDBCController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    // 查询数据库表 user 的所有信息
    // 直接获取数据库的东西—Map
    @GetMapping("/userList")
    public List<Map<String,Object>> userList() {
        String sql = "select * from usertest";
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql);
        return maps;
    }

    @GetMapping("/addAddress")
    public String addUser() {
        String sql = "insert into tmall.usertest(id, username, pwd) values(3, 'Alixs', 666789)";
        jdbcTemplate.update(sql);
        return "update-ok";
    }

    @GetMapping("/updateUser/{id}")
    public String updateUser(@PathVariable("id") int id) {
        String sql = "update tmall.usertest set username  = ?,pwd = ? where id = " + id;
        //封装
        Object[] objects = new Object[2];

        objects[0] = "Maryland";
        objects[1] = 444789;

        jdbcTemplate.update(sql,objects);
        return "update-ok";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        String sql = "delete from tmall.usertest where id = ?";
        jdbcTemplate.update(sql,id);
        return "update-ok";
    }
}

