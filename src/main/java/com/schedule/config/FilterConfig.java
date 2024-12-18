package com.schedule.config;


import com.schedule.filter.CustomFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class FilterConfig implements WebMvcConfigurer { // 설정한 필터들을 한곳에 모아 필터의 우선 순위 혹은 필터로써의 역할을 하게끔 해주는 클래스

    @Bean
    protected FilterRegistrationBean RegistrationBean() { //  protected? // private 쓰려했는데 거부 당함

        FilterRegistrationBean<Filter> filterFilterRegistrationBean = new FilterRegistrationBean<>(); //TODO 공부해야할 부분

        filterFilterRegistrationBean.setFilter(new CustomFilter()); // 생성했던 필터 객체를 넣어주는

        filterFilterRegistrationBean.setOrder(1); // 우선 순위를 정하는

        filterFilterRegistrationBean.addUrlPatterns("/*"); // 필터를 탈 url 설정 // 여기에 바로 schedule 쓰면 스케줄만 돌게됨

        return filterFilterRegistrationBean;
    }

}
