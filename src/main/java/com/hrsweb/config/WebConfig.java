package com.hrsweb.config;

import com.hrsweb.intercept.UserLoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptor = registry.addInterceptor(new UserLoginInterceptor());
        interceptor.excludePathPatterns("/page/login.html","/manger/login","/api/**","/css/**","/images/**","/js/**","/lib/**","/checkCode");
        interceptor.addPathPatterns("/**");
    }

    /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 需要告知系统，这是需要被当成静态文件的
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }*/
}
