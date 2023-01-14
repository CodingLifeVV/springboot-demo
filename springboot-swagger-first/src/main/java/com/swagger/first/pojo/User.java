package com.swagger.first.pojo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;

@Api
@ApiModel("用户实体类-User")
public class User {
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("获取用户名")
    public String getUsername() {
        return username;
    }

    public void setUsername( @ApiParam("方法参数-用户名") String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
