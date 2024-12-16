package com.schedule.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    // TODO validation을 쓰기 위해선 먼저 스프링 라이브러리?를 받아야한다
    //  implementation 'org.springframework.boot:spring-boot-starter-validation'

    @NotNull
    @Pattern(regexp = "\\w+@\\w+.\\w+(.\\w+)?")
    // TODO Pattern 어노테이션의 매개변수 설명, https://pixx.tistory.com/365
    // TODO https://inpa.tistory.com/entry/JAVA-%E2%98%95-%EC%A0%95%EA%B7%9C%EC%8B%9DRegular-Expression-%EC%82%AC%EC%9A%A9%EB%B2%95-%EC%A0%95%EB%A6%AC 자료 참고
    // TODO https://www.regexplanet.com/advanced/java/index.html 정규화 테스트 사이트
    private String email;

    @NotNull
    @Size(min = 2,max=10) // Range 는 수의 범위를 지정 // Size 는 크기의 범위!
    private String password;

    public LoginRequestDto() {
    }
}
