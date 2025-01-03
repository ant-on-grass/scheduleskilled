package com.schedule.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleRequestDto {

//    @NotNull
//    private Long user_id; //TODO 로그인 세팅을 하면서 session에서 email을 받아 해당 로그인 계정이 알맞는 권한을 가지는지를 판단하기에, 해당 부분 생략

    @NotNull(message = "빈칸으로 제출할 수 없습니다")
    private String title;

    @NotNull(message = "빈칸으로 제출할 수 없습니다")
    private String contents;

    public ScheduleRequestDto() {
    }
}
