package com.springsecurity.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 定制请求的授权规则
 */
@EnableWebSecurity // 开启 WebSecurity 模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭 csrf 功能: 跨站请求伪造, 默认只能通过 post 方式提交 logout 请求
        http.csrf().disable();
        //配置不需要登陆验证
        http.authorizeRequests().anyRequest().permitAll().and().logout().permitAll();

        http.authorizeHttpRequests().antMatchers("/").permitAll() // 首页所有人可以访问
                .antMatchers("/level1/**").hasRole("vip1") // 拥有 vip1 权限的角色可以访问 level1 下的页面
                .antMatchers("/level2/**").hasRole("vip2") // 拥有 vip2 权限的角色可以访问 level2 下的页面
                .antMatchers("/level3/**").hasRole("vip3");// 拥有 vip3 权限的角色可以访问 level3 下的页面

        // 开启自动配置的登录功能
        // /login 请求来到登录页
        // /login?error 重定向到这里表示登录失败
        // http.formLogin();
        // 自定义登录页面
        http.formLogin().loginPage("/toLogin").usernameParameter("uname").passwordParameter("pwd");
        //自定义登录页面, 登录成功之后跳转 URL 路径
        //http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");


        // 开启自动配置的注销的功能
        // 开启自动配置的注销的功能 /logout 为注销请求
        //http.logout();

        // .logoutSuccessUrl("/"); 注销成功来到首页
        http.logout().logoutSuccessUrl("/");

        //记住我
        //http.rememberMe();
        //定制记住我的参数
        http.rememberMe().rememberMeParameter("remember");

    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        /*//在内存中定义，也可以在 jdbc 中去拿....
        auth.inMemoryAuthentication()
                .withUser("wyj").password("123456").roles("vip2","vip3")          //用户 wyj 具有权限 vip2 和 vip3
                .and()
                .withUser("root").password("123456").roles("vip1","vip2","vip3")  //用户 root 具有权限 vip1、vip2 和 vip3
                .and()
                .withUser("guest").password("123456").roles("vip1","vip2");       //用户 guest 具有权限 vip1 和 vip2
                */

        // Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
        // 要想我们的项目还能够正常登陆，需要修改一下 configure 中的代码。我们要将前端传过来的密码进行某种方式加密
        // spring security 官方推荐的是使用bcrypt加密方式。

        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("wyj").password(new BCryptPasswordEncoder().encode("123456")).roles("vip2","vip3")
                .and()
                .withUser("root").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2","vip3")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("vip1","vip2");




    }


}
