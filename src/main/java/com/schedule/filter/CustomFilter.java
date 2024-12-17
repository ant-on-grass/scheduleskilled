package com.schedule.filter;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;


@Slf4j
public class CustomFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse , FilterChain filterChain) throws ServletException, IOException {

        HttpServletRequest request =(HttpServletRequest) servletRequest;

        log.info("session 확인");
        //log.info(String.valueOf(request.getSession()));


            for (String s : loginNecessaryUrl) {
                //log.info("test1");

                String requestString = request.getRequestURL().toString();

                if(requestString.contains(s)) { // 큰 범위가 바깥 , 작은 것이 안쪽 // string 버퍼 : 스트링을 만들기 위한 애들
                    // request.getRequestURL() 스트링 타입이 아니라 스트링 버퍼 타입! , s는 striing 그래서 비교가 안됬다!

                    //log.info("test2");

                    HttpSession checkSession = request.getSession(false);

                    if(checkSession == null || checkSession.getAttribute("sessionID") == null) { // TODO sessionID 는 안만들고 해도됨
                        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"로그인이 필요합니다");
                    } else {
                        log.info("로그인 확인");
                        log.info(String.valueOf(request.getSession()));
                    }
                }
            }
        //log.info("test3");
        filterChain.doFilter(servletRequest,servletResponse);
    }
// * 이걸 써서
    private static final String[] loginNecessaryUrl = {"/schedules","/users/"};
}
