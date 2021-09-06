package com.liang.thymeleaf.config;

import com.liang.thymeleaf.interceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 解决中文乱码的问题
     * @return
     */
/*

    @Bean
    public FilterRegistrationBean characterEncodingFilterRegistrationBean(){

        //创建强制字符过滤器
        CharacterEncodingFilter encodingFilter=new CharacterEncodingFilter();
        //强制使用指定字符编码
        encodingFilter.setForceEncoding(true);
        //设置指定字符编码
        encodingFilter.setEncoding("utf-8");
        FilterRegistrationBean filterRegistrationBean=new FilterRegistrationBean();

        //设置字符编码过滤器
        filterRegistrationBean.setFilter(encodingFilter);
        //设置字符编码过滤器路径
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
*/

    /*通过这里面的配置：不需要为每一个访问模板页面单独开发一个controller请求了*/
    @Override
    //ViewController 请求路径
    public void addViewControllers(ViewControllerRegistry registry) {
        //ViewController 请求路径  setViewName:跳转视图
        registry.addViewController("login").setViewName("login");
        registry.addViewController("test").setViewName("test");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")     //小心静态资源也会被拦截
                .excludePathPatterns("/","/login","/code.do","/login.do","/js/**","/css/**");
    }
}
