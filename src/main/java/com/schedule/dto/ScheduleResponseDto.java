package com.schedule.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NotNull // 모든 것이 null 이면 안되서 한번에 윗단에 적어두었다
public class ScheduleResponseDto {

    //@Size(min = 2,max=10) - response 는 검증을 안하는 데, 잘 못 넣은 듯 하다
    private String userName;

    private String title;

    private String contents;

    private LocalDateTime fixDate;

    private LocalDateTime flexDate;

    ScheduleResponseDto() {}

}
