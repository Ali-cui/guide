package com.dhu.guide.component;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author: Ali.cui
 * @Date: 2019/11/28 16:23
 */
@Configuration
public class MyMvcConfig {
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){
        WebMvcConfigurerAdapter adapter=new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/dashboard").setViewName("main");//main是面板，做客流量统计
                registry.addViewController("/indexofmanager").setViewName("indexofmanager");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
               registry.addInterceptor(new LoginHandlerIncepter()).addPathPatterns("/**")
                       .excludePathPatterns("/index","/login","/login.html","/user/login","/","/test","/test2","/guide","/upload","/player","/audio","/static/**"
                       ,"/player/**","/uploadimageindex","/uploadimage/**","/getimage/**","/showimage/**","/showimages/**","/getimagetotal/**",
                               "/**/*.css", "/**/*.js","/guide/**","/static/**","/gettouristdata","/guide/gethelppoint","/guide/getlocationdata/**");

               registry.addInterceptor(new UserLoginHandlerIncepter()).addPathPatterns("/guide/**","/guide")
                       .excludePathPatterns("/guide/index","/guide/touristlogin","/**/*.css", "/**/*.js","/guide/register","/static/**","/guide/handleregister",
                               "/guide/getlocationdata/**","/**/*.css", "/**/*.js");

            }
        };

        return adapter;
    }
}
