package com.wyj.config;

import com.wyj.service.UserService;
import com.wyj.pojo.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义 UserRealm, 需要继承 AuthorizingRealm
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了=>授权doGetAuthorizationInfo");

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 为所有用户增加 user:add 权限
        //info.addStringPermission("user:add");

        // 获取当前登录用户
        Subject subject = SecurityUtils.getSubject();
        // 获取认证方法中查到的当前登录用户信息 : User 对象
        User currentUser = (User) subject.getPrincipal();
        // 获取当前用户在数据库中查询到的拥有的权限, 并为当前用户设置该权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    // 认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了=>认证doGetAuthenticationInfo");

        // 模拟获取数据库中的用户名和密码
        //String uname = "root";
        //String pwd = "123456";

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;

        /*// 验证用户名是否输入正确
        if (!userToken.getUsername().equals(uname)) {
            // 用户名输入错误, return null 表示抛出异常 UnknownAccountException
            return null;
        }

        // 验证密码是否输入正确, 由 Shiro 自动完成
        return new SimpleAuthenticationInfo("", pwd, "");*/

        User user = userService.queryUserByName(userToken.getUsername());


        // 验证用户名是否输入正确 user == null 表示用户不存在
        if (user == null) {
            // 用户名输入错误, return null 表示抛出异常 UnknownAccountException
            return null;
        }

        // 获取当前 session, 并将当前用户信息存在 session 中
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        session.setAttribute("loginUser", user);

        // 验证密码是否输入正确, 由 Shiro 自动完成
        // user.getPwd() pwd 需要定义为 String 类型, 定义为 int 类型报错
        //return new SimpleAuthenticationInfo("", user.getPwd(), "");
        return new SimpleAuthenticationInfo(user, user.getPwd(), "");
    }
}
