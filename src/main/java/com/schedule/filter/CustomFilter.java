package com.schedule.filter;


import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
public class CustomFilter implements Filter { // controller 의 전단계에서 발생하는 과정으로 2단계? 가 있다
  // TODO 공부가 필요한 내용

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws ServletException, IOException {

    HttpServletRequest request = (HttpServletRequest) servletRequest;

    log.info("session 확인");
    //log.info(String.valueOf(request.getSession()));

    for (String s : loginNecessaryUrl) {
      //log.info("test1");

      //s == /schedules, 2번째 s = /users/
      String requestString = request.getRequestURL().toString(); // /schedules/1/comments/4
      try {
        if (requestString.contains(s)) { // 큰 범위가 바깥 , 작은 것이 안쪽 // string 버퍼 : 스트링을 만들기 위한 애들
          // request.getRequestURL() 스트링 타입이 아니라 스트링 버퍼 타입! , s는 striing 그래서 비교가 안됬다!

          //log.info("test2");

          HttpSession checkSession = request.getSession(false);

          if (checkSession == null
              || checkSession.getAttribute("sessionID") == null) { // TODO 공부가 필요한 내용 - > 공부 완!
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED); //"로그인이 필요합니다"
          } else {
            log.info("로그인 확인");
            log.info(String.valueOf(request.getSession()));
          }
        }
      } catch (ResponseStatusException ex) {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.setStatus(401);
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter()
            .write("{\"Error\": \"" + ex.getMessage() + "\",\"Message\": \"로그인을 해주세요\"}");
        return;
      }
    }
    log.info("test3");
    filterChain.doFilter(servletRequest, servletResponse);
  }

  // * 이걸 써서
  private static final String[] loginNecessaryUrl = {"/schedules",
      "/users/"}; // 로그인이 필요한 url을 거르기 위한 필드
  // 해당 필드는 static을 썻다.. // TODO 알아보아야 한다.
}
