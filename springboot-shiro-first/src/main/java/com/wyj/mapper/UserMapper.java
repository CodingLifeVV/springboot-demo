package com.wyj.mapper;

import com.wyj.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    User queryUserByName(String name);

}

