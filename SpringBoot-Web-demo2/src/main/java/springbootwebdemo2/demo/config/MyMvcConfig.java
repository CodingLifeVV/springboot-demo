package springbootwebdemo2.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Locale;

/**
 * 扩展 SpringMVC
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {
    //取自定义视图解析器作为一个 Bean
    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    //自定义视图解析器
    public static class MyViewResolver implements ViewResolver {
        @Override
        public View resolveViewName(String viewName, Locale locale) throws Exception {
            return null;
        }
    }
}
