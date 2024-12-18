package com.schedule.controller;

import com.schedule.dto.LoginRequestDto;
import com.schedule.service.LoginService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController { // 로그인을 위한!

    private final LoginService loginService; //DI

    @PostMapping
    public ResponseEntity<String> login(@Validated @RequestBody LoginRequestDto dto, HttpServletRequest request) {
        // TODO Session 은 컨트롤러 전? 단계에서 받아지므로 Service 까지 가지고 가지 말고, 여기서 만들고 맞으면 주고 아니면 안주는 식으로
        //  입력 값을 받는 부분에 @Validated 어노 테이션을 붙인다! - validate
        //  validation 은 controller에서!

        loginService.login(dto); // 불린으로 혹은 그렇게 까진는 안해줘도 될듯
                                 //TODO 위 같에 따라 controller에서 받은 세션을 줄지 말지를 결정
        log.info("로그인 성공!");

        HttpSession session = request.getSession(true); // 세션 생성 + 만든 세션 아이디를 쿠키에 담아 브라우저에게 전달
        // if(session == null) {} //TODO < -  HttpSession session = request.getSession(true); 을 함으로 써 그냥 세션이 생성 이러한 조건 안달아줘도 된다!

        log.info("세션 생성 , 세션 아이디 전달");

        //TODO 세션을 담는 db 에 저장되는 데이터 : 세션 id (의미를 알 수 없는 문자들) + 세선 데이터( 키 : 벨류 )
        //TODO 쿠키로 브라우저에 전달되는 것 : 세션 id

        Map<String, String> sessionData = new HashMap<>();

        sessionData.put("email", dto.getEmail()); // 세션 데이터( 키 : 벨류 ) // 이때는 중요정보인 비밀번호 등은 입력하면 안된다!

        session.setAttribute("sessionID",sessionData); //TODO 갑자기 헛갈리는 내용.. 공부 후 velog에 작성할 예정이다. - > 공부 완! 다음번에 제대로 할 예정!

        return ResponseEntity.ok("성공!"); // ResponseEntity<>() 의 객체를 생성하는 것보다 이것을 더 선호한다고 함!
    }
}
