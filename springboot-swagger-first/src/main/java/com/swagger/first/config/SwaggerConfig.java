package com.swagger.first.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration   //配置类
@EnableSwagger2  // 开启 Swagger2 的自动配置
public class SwaggerConfig {

    @Bean
    public Docket docket(Environment environment) {

        // 设置项目处于哪些环境时, 需要开启 swagger
        // Profiles.of("dev", "test") 处于 dev test 环境 开启 swagger
        Profiles of = Profiles.of("dev", "test");
        // 判断当前项目所处环境, 如果为 dev test 环境, 返回 true
        // 通过 enable() 接收此参数判断并决定是否显示
        boolean b = environment.acceptsProfiles(of);

        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                groupName("王宇").
                // 配置是否启用 swagger, 如果是 false, 浏览器将无法访问
                //enable(false).
                enable(b).
                select().
                // 通过 .select() 方法去配置扫描接口, RequestHandlerSelectors 配置如何扫描接口
                apis(RequestHandlerSelectors.basePackage("com.swagger.first.controller")).
                // apis(RequestHandlerSelectors.any()). // 扫描所有, 项目中的所有接口都会被扫描到
                // apis(RequestHandlerSelectors.none()). // 不扫描接口
                // 通过类上的注解扫描, Controller.class : 只扫描有 controller 注解的类中的接口
                // apis(RequestHandlerSelectors.withClassAnnotation(Controller.class)).
                // 通过方法上的注解扫描, GetMapping.class : 只扫描 get 请求
                // apis(RequestHandlerSelectors.withMethodAnnotation(GetMapping.class)).
                // 配置通过 path 过滤, 即这里只扫描请求以 /hello 开头的接口
                // paths(PathSelectors.ant("/hello/**")).
                build();

    }

    @Bean
    public Docket docket1(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                groupName("张宇");
    }

    @Bean
    public Docket docket2(Environment environment) {
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo()).
                groupName("刘宇");
    }

    // 配置文档信息
    public ApiInfo apiInfo() {
        Contact contact = new Contact("联系人名字", "http://xxx.xxx.com/联系人访问链接", "联系人邮箱"); // 联系人信息
        return new ApiInfo(
                "Swagger学习", // 标题
                "学习演示如何配置Swagger", // 描述
                "v1.0", // 版本
                "http://terms.service.url/组织链接", // 组织链接
                contact, // 联系人信息
                "Apach 2.0 许可", // 许可
                "许可链接", // 许可连接
                new ArrayList<>() // 扩展
        );
    }



}
