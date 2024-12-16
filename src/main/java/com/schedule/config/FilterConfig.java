package com.schedule.config;


import com.schedule.filter.CustomFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfig implements WebMvcConfigurer {

    @Bean
    protected FilterRegistrationBean RegistrationBean() { //  protected? // private 쓰려했는데 거부 당함

        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>();

        filterFilterRegistrationBean.setFilter(new CustomFilter());

        filterFilterRegistrationBean.setOrder(1);

        filterFilterRegistrationBean.addUrlPatterns("/*"); // 필터를 탈 url 설정 // 여기에 바로 schedule 쓰면 스케줄만 돌게됨

        return filterFilterRegistrationBean;
    }

}
