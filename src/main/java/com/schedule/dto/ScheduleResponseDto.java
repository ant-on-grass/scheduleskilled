package com.schedule.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@NotNull
public class ScheduleResponseDto {

    @Size(min = 2,max=10)
    private String userName;

    private String title;

    private String contents;

    private LocalDateTime fixDate;

    private LocalDateTime flexDate;

    ScheduleResponseDto() {}

}
