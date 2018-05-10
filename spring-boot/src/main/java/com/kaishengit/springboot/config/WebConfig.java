package com.kaishengit.springboot.config;

import com.kaishengit.springboot.web.filter.MyFilter;
import com.kaishengit.springboot.web.interceptor.MyInterceptor;
import com.kaishengit.springboot.web.listener.MyServletContextListener;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.ServletContextListener;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {


    /**
     * 添加一个过滤器
     * @date 2018/5/10
     * @param []
     * @return org.springframework.boot.web.servlet.FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new MyFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        return  filterRegistrationBean;
    }


    /**
     * 添加一个监听器
     * @date 2018/5/10
     * @param []
     * @return org.springframework.boot.web.servlet.ServletListenerRegistrationBean
     */
    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean(){
        ServletListenerRegistrationBean servletListenerRegistrationBean = new ServletListenerRegistrationBean();
        servletListenerRegistrationBean.setListener(new MyServletContextListener());
        return servletListenerRegistrationBean;
    }

    /**  
     * 添加拦截器
     * @date 2018/5/10
     * @param [registry]  
     * @return void  
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");
    }



}
