package com.springboot.mybatis.springbootmybatis.mapper;

import com.springboot.mybatis.springbootmybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    List<User> queryUserList();

    User queryUserById(Integer id);

    int addUser(User User);

    int updateUser(User user);

    int deleteUser(int id);
}
