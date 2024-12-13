package com.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ScheduleResponseDto {

    private String userName;
    private String title;
    private String contents;
    private LocalDateTime fixDate;
    private LocalDateTime flexDate;

    ScheduleResponseDto() {}

}
