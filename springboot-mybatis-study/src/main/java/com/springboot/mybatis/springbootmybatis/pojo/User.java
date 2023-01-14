package com.springboot.mybatis.springbootmybatis.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //Lombok标签
@AllArgsConstructor
@NoArgsConstructor
public class User {
    //属性名称要与数据库对应表字段名称一致(不区分大小写)
    private int id;
    private String Username;
    private int pwd;
}
