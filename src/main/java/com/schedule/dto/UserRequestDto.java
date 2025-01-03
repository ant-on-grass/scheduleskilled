package com.schedule.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@NotNull(message = "빈칸으로 제출할 수 없습니다")
public class UserRequestDto {

    @Size(min = 2,max=10,message = "형식에 맞지않습니다")
    private String userName;

    @Pattern(regexp = "\\w+@\\w+.\\w+(.\\w+)?",message = "형식에 맞지않는 이메일입니다")
    private String email;

    private String password;

    public UserRequestDto() {
    }
}
